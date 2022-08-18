package com.example.Estate_Twin.asset.service;

import com.example.Estate_Twin.asset.web.dto.*;

public interface AssetService {
    AssetResponseDto getAsset(Long id);
    Long saveAsset(Long houseId, AssetSaveRequestDto assetSaveRequestDto);
    Long updateAsset(Long id, AssetUpdateRequestDto assetUpdateRequestDto) throws Exception;
}
