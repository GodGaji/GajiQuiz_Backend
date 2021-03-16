package kr.godgaji.gajiquiz.domain.Category;

import kr.godgaji.gajiquiz.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Photo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoName;

    private String uuid;

    private String folderPath;

    @Builder
    public Photo(Long id, String photoName, String uuid, String folderPath) {
        this.id = id;
        this.photoName = photoName;
        this.uuid = uuid;
        this.folderPath = folderPath;
    }
}
