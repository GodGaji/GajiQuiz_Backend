package kr.godgaji.gajiquiz.service;

import kr.godgaji.gajiquiz.domain.Member.Member;
import kr.godgaji.gajiquiz.domain.Member.MemberRepository;
import kr.godgaji.gajiquiz.web.dto.OAuthAttributes;
import kr.godgaji.gajiquiz.web.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Log
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession ;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest); //DefaultOAuth2UserService()에서 나옴.

        //vendor 구분(registrationId)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //로그인 진행시 키가 되는 pk값을 의미
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Member kakaoMember = saveOrUpdate(attributes);
        httpSession.setAttribute("SessionUser", new SessionUser(kakaoMember));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(kakaoMember.getRoleValue()))
                , oAuth2User.getAttributes()
                , userNameAttributeName);
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {
        Member kakaoMember = memberRepository.findByEmail(attributes.getEmail())
                .map(member -> member.update(attributes.getName(), attributes.getEmail())) //닉네임은 따로 관리
                .orElse(attributes.toEntity());
        if(kakaoMember.getEmail() == null){
            log.info("saveOrUpdate메소드: email is null");
        }
        log.info("saveOrUpdate메소드: "+kakaoMember.getEmail()+","+kakaoMember.getName());

        return memberRepository.save(kakaoMember);
    }
}
