package kr.godgaji.gajiquiz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.godgaji.gajiquiz.domain.Category.Category;
import kr.godgaji.gajiquiz.domain.Category.CategoryRepository;
import kr.godgaji.gajiquiz.domain.Category.Photo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryServiceTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired CategoryRepository categoryRepository;

    @BeforeEach
    public void beforeEach() {
        categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("카테고리 전체 조회")
    public void findAll() throws Exception {
        mockMvc.perform(get("/api/v1/category/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("카테고리 생성")
    public void create() {
        Category category = Category.builder()
                .name("카테고리 이름")
                .description("카테고리 설명")
                .build();
        Photo photo = Photo.builder()
                .photoName("사진 이름")
                .uuid("1234-5678")
                .folderPath("사진 경로")
                .build();

        category.addPhoto(photo);

        Category savedCategory = categoryRepository.save(category);

        Assertions.assertEquals(savedCategory.getPhoto().getUuid(), "1234-5678");
    }
}
