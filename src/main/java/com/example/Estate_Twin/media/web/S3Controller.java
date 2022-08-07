package com.example.Estate_Twin.media.web;

import com.example.Estate_Twin.media.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Uploader s3Uploader;

    @PostMapping("/image")
    public String upload(@RequestParam("images") MultipartFile multipartFile)throws IOException {
        s3Uploader.upload(multipartFile,"static");
        return "test";
    }
}