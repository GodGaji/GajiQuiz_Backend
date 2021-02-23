package kr.godgaji.gajiquiz.service.member;

import kr.godgaji.gajiquiz.domain.Member.Member;
import kr.godgaji.gajiquiz.domain.Member.MemberRepository;
import kr.godgaji.gajiquiz.web.dto.member.UpdateNickNameRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member updateNickName(UpdateNickNameRequestDto dto) {
        Member member = new Member();
        member.updateNickName(dto.getNickName());
        return memberRepository.save(member);
    }
}
