package com.example.file;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    public String save(MultipartFile file , String... path);
}
