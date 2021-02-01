package kr.godgaji.gajiquiz.web.dto;

import kr.godgaji.gajiquiz.domain.Member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class SessionUser implements Serializable {
    private String name ;
    private String email ;
    private String image ;

    public SessionUser(Member member) {
        this.name = member.getName() ;
        this.email = member.getEmail() ;
        this.image = member.getImage() ;
    }
}
