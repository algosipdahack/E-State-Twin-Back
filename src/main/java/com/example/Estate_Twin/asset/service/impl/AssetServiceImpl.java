package com.example.Estate_Twin.asset.service.impl;

import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.asset.service.AssetService;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.asset.web.dto.AssetUpdateRequestDto;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetDAO assetDAO;

    @Override
    public AssetResponseDto getAsset(Long id) {
        return new AssetResponseDto(assetDAO.findAsset(id));
    }

    @Override
    public Long saveAsset(Long houseId, AssetSaveRequestDto assetSaveRequestDto) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(()->new IllegalArgumentException("해당 집이 없습니다. id = "+houseId));
        assetSaveRequestDto.setHouse(house);
        return assetRepository.save(assetSaveRequestDto.toEntity()).getId();
    }

    @Override
    public Long updateAsset(Long id, AssetUpdateRequestDto assetUpdateRequestDto) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 어셋이 없습니다. id = "+id));
        asset.update(assetUpdateRequestDto.getCategory(), assetUpdateRequestDto.getHouse(),
                assetUpdateRequestDto.getAssetName(), assetUpdateRequestDto.getProductName());
        return id;
    }
}
