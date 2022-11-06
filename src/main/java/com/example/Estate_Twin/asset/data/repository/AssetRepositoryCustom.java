package com.example.Estate_Twin.asset.data.repository;

import com.example.Estate_Twin.asset.data.entity.*;

import java.util.List;

public interface AssetRepositoryCustom {
    List<Asset> findTenantAsset(Long userId, Option option);
    List<Asset> findOwnerAsset(Long userId, Option option);
}
