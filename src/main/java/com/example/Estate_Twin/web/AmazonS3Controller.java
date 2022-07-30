package com.example.Estate_Twin.web;

import com.example.Estate_Twin.service.S3.AwsS3Service;
import com.example.Estate_Twin.web.dto.ApiResult;
import com.example.Estate_Twin.web.dto.FileUploadDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AmazonS3Controller {
    private final AwsS3Service awsS3Service;

    @ApiOperation(value = "Amazon S3에 파일 업로드", notes = "Amazon S3에 파일 업로드")
    @PostMapping("/file")
    public ResponseEntity<ApiResult<List<FileUploadDto>>> uploadFile(@ApiParam(value = "파일들(여러 파일 업로드 가능)",required = true)@RequestPart List<MultipartFile> multipartFile) {
        return ResponseEntity.ok(ApiResult.ok(awsS3Service.uploadFile(multipartFile)));
    }

    @ApiOperation(value = "Amazon S3에 업로드 된 파일 삭제", notes = "Amazon S3에 업로드 된 이미지 삭제")
    @DeleteMapping("/file")
    public ResponseEntity<Void> deleteFile(@ApiParam(value="img 파일 하나 삭제",required = true)@RequestParam String fileName){
        awsS3Service.deleteFile(fileName);
        return ResponseEntity.ok(null);
    }
}
