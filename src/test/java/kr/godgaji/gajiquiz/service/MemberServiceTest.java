package kr.godgaji.gajiquiz.service;

import kr.godgaji.gajiquiz.domain.Member.MemberRepository;
import kr.godgaji.gajiquiz.service.member.MemberService;
import kr.godgaji.gajiquiz.web.dto.member.UpdateNickNameRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("닉네임 설정 통과")
    public void nickName_Update() {
        UpdateNickNameRequestDto dto = UpdateNickNameRequestDto.builder()
                .id(1L)
                .nickName("test")
                .build();

        memberService.updateNickName(dto);

        Assertions.assertEquals(memberRepository.findByNickName("test").getNickName(), dto.getNickName());
    }
}
