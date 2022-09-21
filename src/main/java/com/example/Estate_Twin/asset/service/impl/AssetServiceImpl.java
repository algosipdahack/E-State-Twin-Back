package com.example.Estate_Twin.asset.service.impl;

import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.asset.data.entity.Option;
import com.example.Estate_Twin.asset.service.AssetService;
import com.example.Estate_Twin.asset.web.dto.*;
import com.example.Estate_Twin.media.domain.entity.Media;

import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
    private final AssetDAO assetDAO;
    @Override
    public AssetResponseDto getAsset(Long id) {
        return new AssetResponseDto(assetDAO.findAsset(id));
    }

    @Override
    public AssetResponseDto saveAsset(AssetSaveRequestDto assetSaveRequestDto) {
        return new AssetResponseDto(assetDAO.saveAsset(assetSaveRequestDto.toEntity()));
    }

    @Override
    public AssetResponseDto updateAsset(Long id, AssetUpdateRequestDto assetUpdateRequestDto) {
        Asset asset = assetDAO.updateAsset(id, Category.of(assetUpdateRequestDto.getCategory()),
                Option.of(assetUpdateRequestDto.getOption()),assetUpdateRequestDto.getProductName());
        return new AssetResponseDto(asset);
    }

    @Override
    public Asset addMedia(Long assetId, Media media) {
        return assetDAO.addAssetMedia(assetId,media);
    }

    @Override
    public void clearMedia(Long id) {
        assetDAO.clearMedia(id);
    }

}
