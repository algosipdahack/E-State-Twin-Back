package com.example.Estate_Twin.asset.service;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.web.dto.*;
import com.example.Estate_Twin.media.domain.entity.Media;

public interface AssetService {
    AssetResponseDto getAsset(Long id);
    AssetDto saveAsset(Long houseId, AssetSaveRequestDto assetSaveRequestDto);
    AssetResponseDto updateAsset(Long id, AssetUpdateRequestDto assetUpdateRequestDto);
    Asset addMedia(Long assetId, Media media);
    void clearMedia(Long id);
}
