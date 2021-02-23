package kr.godgaji.gajiquiz.web;

import kr.godgaji.gajiquiz.service.member.MemberService;
import kr.godgaji.gajiquiz.web.dto.member.UpdateNickNameRequestDto;
import kr.godgaji.gajiquiz.web.dto.member.UpdateNickNameResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member/")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("update/nickname")
    public UpdateNickNameResponseDto updateNickName(@Valid @RequestBody UpdateNickNameRequestDto dto) {
        return new UpdateNickNameResponseDto(memberService.updateNickName(dto));
    }
}
