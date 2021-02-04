package kr.godgaji.gajiquiz.domain.SolvedLog;

import kr.godgaji.gajiquiz.domain.BaseEntity;
import kr.godgaji.gajiquiz.domain.Category.Category;
import kr.godgaji.gajiquiz.domain.Member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class SolvedLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime startTime;

    @CreatedDate
    private LocalDateTime endTime;

    private Long correctCount;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="catetory_id")
    private Category category;

    @Builder
    public SolvedLog(LocalDateTime startTime, LocalDateTime endTime, Long correctCount){
        this.startTime = startTime;
        this.endTime = endTime;
        this.correctCount = correctCount;
    }
}
