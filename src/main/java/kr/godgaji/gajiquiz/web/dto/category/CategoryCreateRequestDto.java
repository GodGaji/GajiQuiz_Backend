package kr.godgaji.gajiquiz.web.dto.category;

import kr.godgaji.gajiquiz.domain.Category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class CategoryCreateRequestDto {
    private String name;
    private String description;
    @JsonIgnore
    private MultipartFile multipartFile;

    public Category toEntity() {
        return Category.builder()
                .name(this.name)
                .description(this.description)
                .build();
    }

    @Builder
    public CategoryCreateRequestDto(String name, String description, MultipartFile multipartFile) {
        this.name = name;
        this.description = description;
        this.multipartFile = multipartFile;
    }
}
