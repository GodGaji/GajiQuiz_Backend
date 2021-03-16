package kr.godgaji.gajiquiz.web;

import kr.godgaji.gajiquiz.service.CategoryService;
import kr.godgaji.gajiquiz.web.dto.category.CategoryCreateRequestDto;
import kr.godgaji.gajiquiz.web.dto.category.CategoryListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category/")
public class CategoryApiController {

    private final CategoryService categoryService;

    @GetMapping("all")
    public List<CategoryListResponseDto> listAll() {
        return categoryService.findAll();
    }

    @PostMapping("create")
    public void create(@ModelAttribute CategoryCreateRequestDto dto) throws IOException {
        log.info(dto.toString());
        log.info(dto.getMultipartFile().getOriginalFilename());
        log.info(dto.getMultipartFile().getBytes());
        log.info(dto.getMultipartFile().getContentType());
        log.info(dto.getMultipartFile().getName());
        categoryService.create(dto);
    }
}
