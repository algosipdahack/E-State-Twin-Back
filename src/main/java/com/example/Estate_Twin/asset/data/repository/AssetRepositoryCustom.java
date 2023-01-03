package com.example.Estate_Twin.asset.data.repository;

import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;

import java.util.List;

public interface AssetRepositoryCustom {
    List<AssetResponseDto> findTenantAsset(Long userId, Category category);
    List<AssetResponseDto> findOwnerAsset(Long userId, Long estateId);
}
