package kr.godgaji.gajiquiz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.godgaji.gajiquiz.domain.Category.Photo;
import kr.godgaji.gajiquiz.domain.Category.PhotoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PhotoServiceTest {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired PhotoService photoService;
    @Autowired PhotoRepository photoRepository;

    @BeforeEach
    public void beforeEach() {
        photoRepository.deleteAll();
    }

    @Test
    @DisplayName("사진 읽어오기")
    public void display() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("multipartFile","떡볶이.jpg" , "image/jpeg" , "hello file".getBytes());

        Photo photo = photoService.upload(multipartFile);

        Assertions.assertEquals(photoService.display(photo.getPhotoName())[1], "hello file".getBytes()[1]);
    }

    @Test
    @DisplayName("사진 업로드")
    public void uploadPhoto() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("multipartFile","떡볶이.jpg" , "image/jpeg" , "hello file".getBytes());

        Photo photo = photoService.upload(multipartFile);

        Assertions.assertEquals(photo.getPhotoName(), photo.getUuid() + "_" + "떡볶이.jpg");
    }
}
