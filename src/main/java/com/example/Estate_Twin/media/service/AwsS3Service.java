package com.example.Estate_Twin.media.service;

import com.example.Estate_Twin.media.web.dto.MediaDto;
import com.example.Estate_Twin.media.web.dto.MediaResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AwsS3Service {
    List<MediaResponseDto> uploadEstate(List<MultipartFile> multipartFile, Long estateId, String dirName);
    List<MediaResponseDto> uploadAsset(List<MultipartFile> multipartFile, Long assetId, String dirName);
    List<MediaResponseDto> uploadCheckList(List<MultipartFile> multipartFile, Long checklistId, String dirName);
    List<String> uploadFile(List<MultipartFile> multipartFiles, String dirName);
}
