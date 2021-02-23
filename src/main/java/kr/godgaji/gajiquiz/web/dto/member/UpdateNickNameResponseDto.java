package kr.godgaji.gajiquiz.web.dto.member;

import kr.godgaji.gajiquiz.domain.Member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateNickNameResponseDto {
    private Long id;
    private String name;
    private String nickName;

    @Builder
    public UpdateNickNameResponseDto(Member entity) {
        this.nickName = entity.getNickName();
        this.name = entity.getName();
        this.id = entity.getId();
    }
}