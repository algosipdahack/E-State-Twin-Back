package com.example.Estate_Twin.service.S3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.Estate_Twin.web.dto.FileUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AwsS3Service {
    @Value("$(cloud.aws.s3.bucket)")
    private String bucket;

    private final AmazonS3 amazonS3;

    public List<FileUploadDto> uploadFile(List<MultipartFile> multipartFile) {
        List<FileUploadDto> fileNameList = new ArrayList<>();

        //multipartfile로 들어온 파일 -> filenamelist에 추가
        multipartFile.forEach( file -> {
            String fileName = createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try(InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket,fileName, inputStream,objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            }catch(IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }
            fileNameList.add(new FileUploadDto(fileName));
        });
        return fileNameList;
    }

    public void deleteFile(String fileName) {
        //해당 파일 이름에 맞는 이미지 제거
        amazonS3.deleteObject(new DeleteObjectRequest(bucket,fileName));
    }

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }
}
