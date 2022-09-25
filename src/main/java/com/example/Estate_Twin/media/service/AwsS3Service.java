package com.example.Estate_Twin.media.service;

import com.example.Estate_Twin.media.web.dto.MediaDto;
import com.example.Estate_Twin.media.web.dto.MediaResponseDto;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import com.example.Estate_Twin.media.web.dto.MediaSaveRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AwsS3Service {
    List<MediaResponseDto> uploadEstate(List<MediaSaveMultipartRequestDto> multipartFile, Long estateId, String dirName);
    List<MediaResponseDto> uploadAsset(List<MediaSaveMultipartRequestDto> multipartFile, Long assetId, String dirName);
    List<MediaResponseDto> uploadCheckList(List<MediaSaveMultipartRequestDto> multipartFile, Long checklistId, String dirName);
    List<String> uploadFile(List<MultipartFile> multipartFiles, String dirName);
    MediaResponseDto saveMedia(MediaSaveMultipartRequestDto media);
}
