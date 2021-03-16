package kr.godgaji.gajiquiz.service;

import kr.godgaji.gajiquiz.domain.Category.Category;
import kr.godgaji.gajiquiz.domain.Category.CategoryRepository;
import kr.godgaji.gajiquiz.domain.Category.Photo;
import kr.godgaji.gajiquiz.web.dto.category.CategoryCreateRequestDto;
import kr.godgaji.gajiquiz.web.dto.category.CategoryListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final PhotoService photoService;

    public List<CategoryListResponseDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(CategoryCreateRequestDto dto) throws IOException {
        Category category = dto.toEntity();
        Photo photo = photoService.upload(dto.getMultipartFile());
        category.addPhoto(photo);
        categoryRepository.save(category);
    }
}
