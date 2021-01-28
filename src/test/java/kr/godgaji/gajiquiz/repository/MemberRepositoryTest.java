package kr.godgaji.gajiquiz.repository;

import kr.godgaji.gajiquiz.domain.Member.Member;
import kr.godgaji.gajiquiz.domain.Member.MemberRepository;
import kr.godgaji.gajiquiz.domain.Role.Role;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * 데이터소스의 설정이 정상적인지, JPA를 사용하서 데이터를 제대로 생성, 수정, 삭제하는지 등의 테스트가 가능합니다.
 * 기본적으로 인메모리 데이터베이스를 이용합니다.
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    String email1 = "kim@gmail.com";
    String name1 = "kim";
    String nickName1 = "kimNickName";

    @Test
    public void Member_Email로_조회() {
        Member new1 = Member.builder()
                .email(email1)
                .name(name1)
                .role(Role.USER)
                .nickName(nickName1)
                .build();
        memberRepository.save(new1);
        Member member = memberRepository.findByEmail(email1).orElse(null);
        assertThat(member.getName()).isEqualTo(name1);
    }

    @Test
    public void Member_Nickname으로_조회() {
        Member new1 = Member.builder()
                .email(email1)
                .name(name1)
                .role(Role.USER)
                .nickName(nickName1)
                .build();
        memberRepository.save(new1);
        Member member = memberRepository.findByNickName(nickName1).orElse(null);
        assertThat(member.getName()).isEqualTo(name1);
    }
}
