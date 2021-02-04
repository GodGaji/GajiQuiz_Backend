package kr.godgaji.gajiquiz.domain.Category;


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
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true)
    private String name;

    @Column(length = 10, unique = true)
    private String type;

    @OneToMany(mappedBy = "category")
    private List<SolvedLog> solvedLogs= new ArrayList<>();

    @OneToMany(mappedBy= "category")
    private List<Question> questions = new ArrayList<>();

    @Builder
    public Category(String name, String type){
        this.name = name;
        this.type = type;
    }
}
