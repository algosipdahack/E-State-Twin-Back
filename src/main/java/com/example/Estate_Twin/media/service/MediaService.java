package com.example.Estate_Twin.media.service;

import com.example.Estate_Twin.media.web.dto.*;

public interface MediaService {
    MediaResponseDto getMedia(Long id);
    MediaResponseDto saveMedia(MediaSaveRequestDto mediaSaveRequestDto);
    MediaResponseDto updateMedia(Long id, MediaUpdateRequestDto mediaUpdateRequestDto);
    MediaResponseDto updateCheckList(Long id, Long checkListId);
    MediaResponseDto updateAsset(Long id, Long assetId);
    MediaResponseDto updateEstate(Long id, Long estateId);
}
