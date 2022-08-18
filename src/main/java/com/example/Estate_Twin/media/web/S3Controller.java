package com.example.Estate_Twin.media.web;

import com.example.Estate_Twin.media.service.S3Uploader;
import com.example.Estate_Twin.media.web.dto.MediaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/upload")
public class S3Controller {
    private final S3Uploader s3Uploader;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    //TODO 로그인 구성 완료 후 사용자 이름에 맞는 폴더 구성하기
    //TODO 리턴 값 함수화
    @PostMapping(value = "/estate/{estateId}/photo", consumes = {"multipart/form-data"})
    @ResponseBody()
    //파일 첨부를 안할 수도 있기에 required=false로 설정
    public String estatePhoto(@RequestPart(required = false) List<MultipartFile> multipartFile, @PathVariable Long estateId) throws IOException {
        MediaResponseDto mediaResponseDto = s3Uploader.upload(multipartFile,)
        return s3Uploader.upload(multipartFile,"static");
    }
    @PostMapping(value = "/estate/{estateId}/video", consumes = {"multipart/form-data"})
    @ResponseBody()
    //파일 첨부를 안할 수도 있기에 required=false로 설정
    public String estateVideo(@RequestPart(required = false) List<MultipartFile> multipartFile, @PathVariable Long estateId) throws IOException {

        return s3Uploader.upload(multipartFile,"static");
    }
    @PostMapping(value = "/asset/{assetId}", consumes = {"multipart/form-data"})
    @ResponseBody()
    //파일 첨부를 안할 수도 있기에 required=false로 설정
    public String assetPhoto(@RequestPart(required = false) List<MultipartFile> multipartFile, @PathVariable Long assetId) throws IOException {

        return s3Uploader.upload(multipartFile,"static");
    }
    @PostMapping(value = "/checklist/{checklistId}", consumes = {"multipart/form-data"})
    @ResponseBody()
    //파일 첨부를 안할 수도 있기에 required=false로 설정
    public String checklistPhoto(@RequestPart(required = false) List<MultipartFile> multipartFile, @PathVariable Long checklistId) throws IOException {

        return s3Uploader.upload(multipartFile,"static");
    }

    @PostMapping(value = "/broker/{brokerId}", consumes = {"multipart/form-data"})
    @ResponseBody()
    //파일 첨부를 안할 수도 있기에 required=false로 설정
    public String brokerPhoto(@RequestPart(required = false) List<MultipartFile> multipartFile, @PathVariable Long brokerId) throws IOException {

        return s3Uploader.upload(multipartFile,"static");
    }

}