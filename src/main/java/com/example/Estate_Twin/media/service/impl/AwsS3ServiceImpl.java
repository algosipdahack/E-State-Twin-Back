package com.example.Estate_Twin.media.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.example.Estate_Twin.asset.service.AssetService;
import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.media.service.MediaService;
import com.example.Estate_Twin.media.web.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.*;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:application-s3.properties")
@Slf4j
public class AwsS3ServiceImpl {
    private final AmazonS3Client amazonS3Client;
    private final MediaService mediaService;
    private final EstateService estateService;
    private final AssetService assetService;
    private final CheckListService checkListService;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름

    public List<MediaResponseDto> uploadEstate(List<MultipartFile> multipartFile, Long estateId, String dirName) {
        //파일 이름 받아오기
        List<String> fileNameList = uploadFile(multipartFile,dirName);
        List<Media> mediaList = new ArrayList<>();
        List<MediaResponseDto> mediaResponseDtoList = new ArrayList<>();

        //파일 이름에 따라 media 객체 생성하기
        fileNameList.forEach(file -> {
            MediaResponseDto mediaResponseDto = mediaService.saveMedia(new MediaSaveRequestDto(file, getFilePath(file)));
            mediaResponseDtoList.add(mediaResponseDto);

            mediaService.updateEstate(mediaResponseDto.toEntity().getId(), estateId);
            mediaList.add(mediaService.saveMedia(new MediaSaveRequestDto(file, getFilePath(file))).toEntity());
        });

        //media 객체로 estate객체 add해주기
        estateService.addMedia(estateId,mediaList);
        return mediaResponseDtoList;
    }

    public List<MediaResponseDto> uploadAsset(List<MultipartFile> multipartFile, Long assetId, String dirName) {
        //파일 이름 받아오기
        List<String> fileNameList = uploadFile(multipartFile,dirName);
        List<Media> mediaList = new ArrayList<>();
        List<MediaResponseDto> mediaResponseDtoList = new ArrayList<>();

        //파일 이름에 따라 media 객체 생성하기
        fileNameList.forEach(file -> {
            MediaResponseDto mediaResponseDto = mediaService.saveMedia(new MediaSaveRequestDto(file, getFilePath(file)));
            mediaResponseDtoList.add(mediaResponseDto);

            mediaService.updateAsset(mediaResponseDto.toEntity().getId(), assetId);
            mediaList.add(mediaService.saveMedia(new MediaSaveRequestDto(file, getFilePath(file))).toEntity());
        });

        //media 객체로 asset객체 add해주기
        assetService.addMedia(assetId,mediaList);
        return mediaResponseDtoList;
    }

    public List<MediaResponseDto> uploadCheckList(List<MultipartFile> multipartFile, Long checklistId, String dirName) throws IOException {
        List<String> fileNameList = uploadFile(multipartFile,dirName);
        List<Media> mediaList = new ArrayList<>();
        List<MediaResponseDto> mediaResponseDtoList = new ArrayList<>();

        //파일 이름에 따라 media 객체 생성하기
        fileNameList.forEach(file -> {
            MediaResponseDto mediaResponseDto = mediaService.saveMedia(new MediaSaveRequestDto(file, getFilePath(file)));
            mediaResponseDtoList.add(mediaResponseDto);

            mediaService.updateAsset(mediaResponseDto.toEntity().getId(), checklistId);
            mediaList.add(mediaService.saveMedia(new MediaSaveRequestDto(file, getFilePath(file))).toEntity());
        });

        //media 객체로 checklist객체 add해주기
        checkListService.addMedia(checklistId,mediaList);
        return mediaResponseDtoList;
    }

    //TODO
    /*public List<MediaResponseDto> uploadBroker(List<MultipartFile> multipartFile, Long brokerId, String dirName) throws IOException {
        Media uploadMedia = mediaDAO.saveMedia(new Media()
                .builder()
                .filePath(mediaResponseDto.getFilePath())
                .origFileName(mediaResponseDto.getOrigFileName())
                .build());

        mediaDAO.saveMedia(mediaResponseDto);

        File uploadFile = convert(multipartFile);  // 파일 변환할 수 없으면 에러
        return upload(uploadFile, dirName);
    }*/

    public String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    public String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch(StringIndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일("+fileName+") 입니다.");
        }
    }

    //파일 URL받아오기
    public String getFilePath(String newFileName) {
        return amazonS3Client.getResourceUrl(bucket, newFileName);
    }

    public List<String> uploadFile(List<MultipartFile> multipartFiles, String dirName) {

        List<String> fileNameList = new ArrayList<>();
        multipartFiles.forEach(file -> {
            String fileName = createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            //S3 upload
            try(InputStream inputStream = file.getInputStream()) {
                amazonS3Client.putObject(new PutObjectRequest(bucket, dirName+fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)); // file permission
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }

        });
        return fileNameList;
    }
}
