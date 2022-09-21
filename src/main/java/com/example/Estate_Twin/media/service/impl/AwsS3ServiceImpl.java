package com.example.Estate_Twin.media.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.media.service.*;
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
public class AwsS3ServiceImpl implements AwsS3Service {
    private final AmazonS3Client amazonS3Client;
    private final MediaService mediaService;
    private final EstateDAO estateDAO;
    private final AssetDAO assetDAO;
    private final CheckListService checkListService;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름

    public List<MediaResponseDto> uploadEstate(List<MediaSaveMultipartRequestDto> mediaSaveMultipartRequestDtos, Long estateId, String dirName) {
        //파일 이름 받아오기
        List<MultipartFile> multipartFile = new ArrayList<>();
        mediaSaveMultipartRequestDtos.forEach(mediaSaveMultipartRequestDto -> multipartFile.add(mediaSaveMultipartRequestDto.getImageFile()));

        List<String> fileNameList = uploadFile(multipartFile,dirName);
        List<MediaResponseDto> mediaDtoList = new ArrayList<>();

        //파일 초기화
        estateDAO.clearMedia(estateId);
        //파일 이름에 따라 media 객체 생성하기
        fileNameList.forEach(file -> {
            log.info(file);
            MediaResponseDto mediaDto = mediaService.saveEstateMedia(estateId, new MediaSaveRequestDto(file, getFilePath(file))); // AWS URL
            mediaDtoList.add(mediaDto);
        });

        return mediaDtoList;
    }

    public List<MediaResponseDto> uploadAsset(List<MediaSaveMultipartRequestDto> mediaSaveMultipartRequestDtos, Long assetId, String dirName) {
        //파일 이름 받아오기
        List<MultipartFile> multipartFile = new ArrayList<>();
        mediaSaveMultipartRequestDtos.forEach(mediaSaveMultipartRequestDto -> multipartFile.add(mediaSaveMultipartRequestDto.getImageFile()));

        List<String> fileNameList = uploadFile(multipartFile,dirName);
        List<MediaResponseDto> mediaDtoList = new ArrayList<>();

        //파일 초기화
        assetDAO.clearMedia(assetId);
        //파일 이름에 따라 media 객체 생성하기
        fileNameList.forEach(file -> {
            log.info(file);
            MediaResponseDto mediaResponseDto = mediaService.saveAssetMedia(assetId, new MediaSaveRequestDto(file, getFilePath(file)));
            mediaDtoList.add(mediaResponseDto);
        });

        return mediaDtoList;
    }

    public List<MediaResponseDto> uploadCheckList(List<MediaSaveMultipartRequestDto> mediaSaveMultipartRequestDtos, Long checklistId, String dirName) {
        //파일 이름 받아오기
        List<MultipartFile> multipartFile = new ArrayList<>();
        mediaSaveMultipartRequestDtos.forEach(mediaSaveMultipartRequestDto -> multipartFile.add(mediaSaveMultipartRequestDto.getImageFile()));

        List<String> fileNameList = uploadFile(multipartFile,dirName);
        List<MediaResponseDto> mediaDtoList = new ArrayList<>();

        //파일 초기화
        checkListService.clearMedia(checklistId);
        //파일 이름에 따라 media 객체 생성하기
        fileNameList.forEach(file -> {
            log.info(file);
            MediaResponseDto mediaDto = mediaService.saveCheckListMedia(checklistId, new MediaSaveRequestDto(file, getFilePath(file)));
            mediaDtoList.add(mediaDto);
        });

        return mediaDtoList;
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
            //파일 확장자 검증
            String fileName = createFileName(file.getOriginalFilename());
            boolean flag = checkExt(fileName); //사진이면 true, 동영상이면 false
            String newDirName = flag ? dirName +"/photo" : dirName +"/video";

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());
            fileNameList.add(fileName);
            //S3 upload
            try(InputStream inputStream = file.getInputStream()) {
                amazonS3Client.putObject(new PutObjectRequest(bucket, newDirName+"/"+fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)); // file permission
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }

        });
        return fileNameList;
    }

    public boolean checkExt(String fileName) {
        String ext = getFileExtension(fileName);
        boolean flag;
        // 사진일 때
        if (Objects.equals(ext, ".jpg") || Objects.equals(ext, ".png") || Objects.equals(ext, ".jpeg") || Objects.equals(ext, ".bmp")) {
            flag = true;
        } else if (Objects.equals(ext, ".mp4")) { //동영상일 때
            flag = false;
        } else { // 이상한 확장자일 때
            throw new IllegalArgumentException("사진은 jpg, png, jpeg, bmp 확장자만 가능하고 동영상은 mp4 확장자만 가능합니다!");
        }
        return flag;
    }
}
