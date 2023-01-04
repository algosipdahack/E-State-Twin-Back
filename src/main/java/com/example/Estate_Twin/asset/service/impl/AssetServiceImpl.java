package com.example.Estate_Twin.asset.service.impl;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.asset.service.AssetService;
import com.example.Estate_Twin.asset.web.dto.*;

import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
    private AssetRepository assetRepository;
    private EstateRepository estateRepository;

    @Override
    public AssetResponseDto getAsset(Long assetId) {
        return new AssetResponseDto(assetRepository.findByIdUsingFetchJoinCheckList(assetId)
                .orElseThrow(()-> new CheckHouseException(ErrorCode.ASSET_NOT_FOUND)));
    }

    @Override
    public AssetSummaryDto saveAsset(Long estateId, AssetSaveRequestDto assetSaveRequestDto) {
        Asset asset = assetSaveRequestDto.toEntity();
        asset.setEstate(estateRepository.findById(estateId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND)));
        return new AssetSummaryDto(assetRepository.save(asset));
    }

    @Override
    public AssetResponseDto updateAsset(Long assetId, AssetUpdateRequestDto assetUpdateRequestDto) {
        Asset asset = assetRepository.findByIdUsingFetchJoinCheckList(assetId)
                .orElseThrow(()-> new CheckHouseException(ErrorCode.ASSET_NOT_FOUND)).update(assetUpdateRequestDto);
        return new AssetResponseDto(asset);
    }
}
