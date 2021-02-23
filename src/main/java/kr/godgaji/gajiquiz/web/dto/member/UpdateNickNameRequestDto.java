package kr.godgaji.gajiquiz.web.dto.member;

import kr.godgaji.gajiquiz.domain.Member.NickNameUnique;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
@ToString
public class UpdateNickNameRequestDto {

    @NotNull(message = "닉네임은 NULL일 수 없습니다.")
    @NotEmpty(message = "닉네임은 비어 있을 수 없습니다.")
    @NotBlank(message = "닉네임은 공백이 있을 수 없습니다.")
    @Size(min = 2, max = 8, message = "닉네임의 크기는 2에서 8 사이어야 합니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\\*]+$", message = "닉네임은 한글,영문,숫자만 가능합니다.")
    @NickNameUnique(message = "이미 사용중인 닉네임 입니다.")
    private String nickName;

    @NotNull(message = "사용자 정보를 찾을 수 없습니다.")
    private Long id;

    @Builder
    public UpdateNickNameRequestDto(String nickName, Long id) {
        this.nickName = nickName;
        this.id = id;
    }
}
