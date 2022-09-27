package com.example.Estate_Twin.asset.service;

import com.example.Estate_Twin.asset.web.dto.*;

public interface AssetService {
    AssetResponseDto getAsset(Long id);
    AssetResponseDto saveAsset(Long estateId, AssetSaveRequestDto assetSaveRequestDto);
    AssetResponseDto updateAsset(Long id, AssetUpdateRequestDto assetUpdateRequestDto);
}
