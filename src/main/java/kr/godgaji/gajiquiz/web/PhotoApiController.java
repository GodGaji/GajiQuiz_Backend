package kr.godgaji.gajiquiz.web;

import kr.godgaji.gajiquiz.domain.Category.Photo;
import kr.godgaji.gajiquiz.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/photo/")
public class PhotoApiController {

    private final PhotoService photoService;

    @GetMapping(value = "display", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getPhoto(@RequestParam String fileName) throws IOException {
        return photoService.display(fileName);
    }

    @PostMapping("upload")
    public Photo createFile(MultipartFile multipartFile) throws IOException {
        return photoService.upload(multipartFile);
    }
}
