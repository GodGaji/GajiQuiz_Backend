package kr.godgaji.gajiquiz.web.dto.category;

import kr.godgaji.gajiquiz.domain.Category.Category;
import lombok.Getter;

@Getter
public class CategoryListResponseDto {
    private Long categoryId;
    private String name;
    private String description;
    private String photoName;

    public CategoryListResponseDto(Category category) {
        this.categoryId = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.photoName = category.getPhoto().getPhotoName();
    }
}
