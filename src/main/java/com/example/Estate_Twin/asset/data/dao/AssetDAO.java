package com.example.Estate_Twin.asset.data.dao;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.web.dto.AssetUpdateRequestDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;


public interface AssetDAO {
    Asset updateAsset(Long id, AssetUpdateRequestDto updateRequestDto);
    Asset findAsset(Long id);
    Asset saveAsset(Estate estate, Asset asset);
}
