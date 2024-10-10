package com.example.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@Service
@RequiredArgsConstructor
public class S3ServiceDummy implements S3Service {

    @Override
    public String save(MultipartFile file, String... path) {


        try {
            if (!isImageCheck(file.getOriginalFilename())) {
                throw new RuntimeException(file.getOriginalFilename() + "은 이미지가 아닙니다.");
            }
            String filePath = String.join("/", path) + "/" ;



            return filePath;
        } catch (Exception e) {
            throw new RuntimeException("파일 저장 실패");
        }
    }

    public boolean isImageCheck(String fileName) {
        int dot = fileName.lastIndexOf(".");
        String extension = fileName.substring(dot).toLowerCase();
        String[] extensionList = {"png", "jpg", "jpeg", "gif", "img"};
        if (List.of(extensionList).contains(extension)) {
            return false;
        }
        return true;
    }
}
