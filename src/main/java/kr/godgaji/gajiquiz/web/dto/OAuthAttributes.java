package kr.godgaji.gajiquiz.web.dto;

import kr.godgaji.gajiquiz.domain.Member.Member;
import kr.godgaji.gajiquiz.domain.Role.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * vendor에 따라 객체 build
 */
@NoArgsConstructor
@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;

    private String registrationId;

    private String nameAttributeKey;

    private String name;

    private String email;

    private String image;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email
            , String registrationId, String image) {
        this.attributes = attributes;
        this.registrationId = registrationId;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.image = image;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofKakao(registrationId, "id", attributes);
    }

    private static OAuthAttributes ofKakao(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account"); //email없음
        Map<String, Object> profile = (Map<String, Object>) response.get("profile"); //email없음
        return OAuthAttributes.builder()
                .name((String) profile.get("nickname"))
                .email((String) response.get("email"))
                .image((String) profile.get("profile_image_url"))
                .attributes(attributes)
                .registrationId(registrationId)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity(){

        return Member.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .image(image)
                .build();
    }
}
