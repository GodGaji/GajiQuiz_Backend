package kr.godgaji.gajiquiz.domain.Member;

import kr.godgaji.gajiquiz.domain.BaseEntity;
import kr.godgaji.gajiquiz.domain.Question.Question;
import kr.godgaji.gajiquiz.domain.SolvedLog.SolvedLog;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(length = 15)
    private String name;

    @Column(length = 25, unique = true)
    private String email;

    @Column(length = 10, unique = true)
    private String nickName;

    @OneToMany(mappedBy = "member")
    private List<SolvedLog> solvedLogs= new ArrayList<>();

    @OneToMany(mappedBy="member")
    private List<Question> questions = new ArrayList<>();

    @Builder
    public Member(Role role, String name, String email, String nickName){
        this.role = role;
        this.name = name;
        this.email= email;
        this.nickName = nickName;
    }
}
