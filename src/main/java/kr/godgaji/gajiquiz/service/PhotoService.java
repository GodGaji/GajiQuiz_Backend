package kr.godgaji.gajiquiz.service;

import kr.godgaji.gajiquiz.domain.Category.Photo;
import kr.godgaji.gajiquiz.domain.Category.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    public byte[] display(String fileName) throws IOException {
        String srcFileName = URLDecoder.decode(fileName, "UTF-8");
        File file = new File(uploadPath + File.separator + srcFileName);
        return FileCopyUtils.copyToByteArray(file);
    }

    public Photo upload(MultipartFile multipartFile) throws IOException {
        String originalName = multipartFile.getOriginalFilename();
        String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
        String uuid = UUID.randomUUID().toString();
        String saveName = uploadPath + File.separator + uuid + "_" + fileName;
        Path path = Paths.get(saveName);
        multipartFile.transferTo(path);
        return Photo.builder()
                .photoName(uuid + "_" + fileName)
                .uuid(uuid)
                .folderPath(path.toString())
                .build();
    }
}
