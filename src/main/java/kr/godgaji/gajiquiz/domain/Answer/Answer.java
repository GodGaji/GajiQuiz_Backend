package kr.godgaji.gajiquiz.domain.Answer;

import kr.godgaji.gajiquiz.domain.BaseEntity;
import kr.godgaji.gajiquiz.domain.Question.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String type;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 10)
    private String correctType;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Builder
    public Answer(String name, String type, String content, String correctType) {
        this.name = name;
        this.type = type;
        this.content = content;
        this.correctType = correctType;
    }
}
