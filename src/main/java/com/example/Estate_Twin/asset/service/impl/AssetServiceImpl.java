package com.example.Estate_Twin.asset.service.impl;

import com.example.Estate_Twin.asset.data.dao.impl.AssetDAOImpl;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.service.AssetService;
import com.example.Estate_Twin.asset.web.dto.*;

import com.example.Estate_Twin.checklist.data.dao.impl.CheckListDAOImpl;
import com.example.Estate_Twin.estate.domain.dao.impl.EstateDAOImpl;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
    private final AssetDAOImpl assetDAO;
    private final EstateDAOImpl estateDAO;
    private final CheckListDAOImpl checkListDAO;
    @Override
    public AssetResponseDto getAsset(Long assetId) {
        return new AssetResponseDto(assetDAO.findAsset(assetId),checkListDAO.findCheckListsByAssetId(assetId));
    }

    @Override
    public AssetResponseDto saveAsset(Long estateId, AssetSaveRequestDto assetSaveRequestDto) {
        Asset asset = assetDAO.saveAsset(estateDAO.findEstate(estateId), assetSaveRequestDto.toEntity());
        return new AssetResponseDto(asset,checkListDAO.findCheckListsByAssetId(asset.getId()));
    }

    @Override
    public AssetResponseDto updateAsset(Long assetId, AssetUpdateRequestDto assetUpdateRequestDto) {
        Asset asset = assetDAO.updateAsset(assetId, assetUpdateRequestDto);
        return new AssetResponseDto(asset, checkListDAO.findCheckListsByAssetId(asset.getId()));

    }
}
