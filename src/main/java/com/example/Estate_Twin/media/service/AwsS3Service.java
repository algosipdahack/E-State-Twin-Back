package com.example.Estate_Twin.media.service;

import com.example.Estate_Twin.media.web.dto.MediaDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AwsS3Service {
    List<MediaDto> uploadEstate(List<MultipartFile> multipartFile, Long estateId, String dirName);
    List<MediaDto> uploadAsset(List<MultipartFile> multipartFile, Long assetId, String dirName);
    List<MediaDto> uploadCheckList(List<MultipartFile> multipartFile, Long checklistId, String dirName);
}
