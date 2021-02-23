package kr.godgaji.gajiquiz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.godgaji.gajiquiz.domain.Member.Member;
import kr.godgaji.gajiquiz.domain.Member.MemberRepository;
import kr.godgaji.gajiquiz.web.dto.member.UpdateNickNameRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NickNameValidTest {

    @Autowired MockMvc mockMvc;
    @Autowired MemberRepository memberRepository;
    @Autowired ObjectMapper objectMapper;
    @Autowired Validator validator;

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("닉네임 규칙 검증 통과")
    public void nickName_Valid() throws Exception {
        UpdateNickNameRequestDto dto = UpdateNickNameRequestDto.builder()
                .id(1L)
                .nickName("홍길동")
                .build();

        Set<ConstraintViolation<UpdateNickNameRequestDto>> constraintViolations = validator.validate(dto);

        mockMvc.perform(post("/api/v1/member/update/nickname")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("닉네임 입력값 에러")
    public void nickName_Valid_Error() throws Exception {
        UpdateNickNameRequestDto dto = UpdateNickNameRequestDto.builder()
                .id(1L)
                .nickName("riyenas0925!")
                .build();

        Set<ConstraintViolation<UpdateNickNameRequestDto>> constraintViolations = validator.validate(dto);

        mockMvc.perform(post("/api/v1/member/update/nickname")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        assertThat(constraintViolations)
                .extracting(ConstraintViolation::getMessage)
                .containsOnly(
                        "닉네임은 한글,영문,숫자만 가능합니다.",
                        "닉네임의 크기는 2에서 8 사이어야 합니다."
                );
    }

    @Test
    @DisplayName("닉네임 NULL, Blank, Empty 에러")
    public void nickName_Not_null() throws Exception {
        UpdateNickNameRequestDto dto = UpdateNickNameRequestDto.builder()
                .id(1L)
                .nickName(null)
                .build();

        Set<ConstraintViolation<UpdateNickNameRequestDto>> constraintViolations = validator.validate(dto);

        mockMvc.perform(post("/api/v1/member/update/nickname")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        assertThat(constraintViolations)
                .extracting(ConstraintViolation::getMessage)
                .containsOnly(
                        "닉네임은 공백이 있을 수 없습니다.",
                        "닉네임은 NULL일 수 없습니다.",
                        "닉네임은 비어 있을 수 없습니다."
                );
    }

    @Test
    @DisplayName("닉네임 중복 에러")
    public void nickName_Not_Duplicate() throws Exception {
        UpdateNickNameRequestDto dto = UpdateNickNameRequestDto.builder()
                .id(1L)
                .nickName("홍길동")
                .build();

        Member member = new Member().updateNickName(dto.getNickName());
        memberRepository.save(member);

        Set<ConstraintViolation<UpdateNickNameRequestDto>> constraintViolations = validator.validate(dto);

        mockMvc.perform(post("/api/v1/member/update/nickname")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        assertThat(constraintViolations)
                .extracting(ConstraintViolation::getMessage)
                .containsOnly(
                        "이미 사용중인 닉네임 입니다."
                );
    }
}
