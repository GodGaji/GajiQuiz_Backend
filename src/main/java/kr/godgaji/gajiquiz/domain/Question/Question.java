package kr.godgaji.gajiquiz.domain.Question;

import kr.godgaji.gajiquiz.domain.Answer.Answer;
import kr.godgaji.gajiquiz.domain.BaseEntity;
import kr.godgaji.gajiquiz.domain.Category.Category;
import kr.godgaji.gajiquiz.domain.Member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String tag;

    @Column(columnDefinition = "TEXT", unique = true)
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @Builder
    public Question(String tag, String content){
        this.tag = tag;
        this.content = content;
    }
}
