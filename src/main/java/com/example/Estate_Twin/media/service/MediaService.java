package com.example.Estate_Twin.media.service;

import com.example.Estate_Twin.media.web.dto.*;

public interface MediaService {
    MediaResponseDto getMedia(Long id);
    MediaResponseDto saveMedia(MediaSaveRequestDto mediaSaveRequestDto);
    MediaResponseDto updateMedia(Long id, MediaUpdateRequestDto mediaUpdateRequestDto);
    MediaResponseDto saveEstateMedia(Long estateId, MediaSaveRequestDto mediaSaveRequestDto);
    MediaResponseDto saveAssetMedia(Long assetId, MediaSaveRequestDto mediaSaveRequestDto);
    MediaResponseDto saveCheckListMedia(Long checklistId, MediaSaveRequestDto mediaSaveRequestDto);
}
