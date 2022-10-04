package com.example.Estate_Twin.asset.service.impl;

import com.example.Estate_Twin.asset.data.dao.impl.AssetDAOImpl;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.service.AssetService;
import com.example.Estate_Twin.asset.web.dto.*;

import com.example.Estate_Twin.estate.domain.dao.impl.EstateDAOImpl;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
    private final AssetDAOImpl assetDAO;
    private final EstateDAOImpl estateDAO;
    @Override
    public AssetResponseDto getAsset(Long id) {
        return new AssetResponseDto(assetDAO.findAsset(id));
    }

    @Override
    public AssetResponseDto saveAsset(Long estateId, AssetSaveRequestDto assetSaveRequestDto) {
        return new AssetResponseDto(assetDAO.saveAsset(estateDAO.findEstate(estateId), assetSaveRequestDto.toEntity()));
    }

    @Override
    public AssetResponseDto updateAsset(Long id, AssetUpdateRequestDto assetUpdateRequestDto) {
        Asset asset = assetDAO.updateAsset(id, assetUpdateRequestDto);
        return new AssetResponseDto(asset);
    }

}
