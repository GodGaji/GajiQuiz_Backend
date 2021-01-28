package kr.godgaji.gajiquiz.domain.Member;

import kr.godgaji.gajiquiz.domain.BaseEntity;
import kr.godgaji.gajiquiz.domain.Role.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true)
    private String nickName;

    private String image;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    public Member(Role role, String name, String nickName, String email, String image) {
        this.role = role;
        this.name = name;
        this.email = email;
        this.nickName = nickName;
        this.image = image;
    }

    public Member update(String name, String email) {
        this.name = name;
        this.email = email;
        return this;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }


}
