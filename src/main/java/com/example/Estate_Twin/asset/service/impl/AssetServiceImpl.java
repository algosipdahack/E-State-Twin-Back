package com.example.Estate_Twin.asset.service.impl;

import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.service.AssetService;
import com.example.Estate_Twin.asset.web.dto.*;
import com.example.Estate_Twin.house.domain.dao.HouseDAO;
import com.example.Estate_Twin.media.domain.entity.Media;

import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
    private final AssetDAO assetDAO;
    private final HouseDAO houseDAO;

    @Override
    public AssetResponseDto getAsset(Long id) {
        return new AssetResponseDto(assetDAO.findAsset(id));
    }

    @Override
    public AssetResponseDto saveAsset(Long houseId, AssetSaveRequestDto assetSaveRequestDto) {
        return new AssetResponseDto(assetDAO.saveAsset(assetSaveRequestDto.toEntity(),houseDAO.findHouse(houseId)));
    }

    @Override
    public AssetResponseDto updateAsset(Long id, AssetUpdateRequestDto assetUpdateRequestDto) {
        Asset asset = assetDAO.updateAsset(id, assetUpdateRequestDto.getCategory(),
                assetUpdateRequestDto.getAssetName(),assetUpdateRequestDto.getProductName());
        return new AssetResponseDto(asset);
    }

    @Override
    public Asset addMedia(Long assetId, Media media) {
        return assetDAO.addAssetMedia(assetId,media);
    }

    @Override
    public void clearMedia(Long id) {
        assetDAO.clearMedia(assetDAO.findAsset(id));
    }

}
