package com.example.Estate_Twin.asset.service;

import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.asset.domain.repository.AssetRepository;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.asset.web.dto.AssetUpdateRequestDto;
import com.example.Estate_Twin.house.domain.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;
    private final HouseRepository houseRepository;

    public AssetResponseDto findById(Long id) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 어셋이 없습니다. id = "+id));
        return new AssetResponseDto(asset);
    }

    @Transactional
    public Long save(Long houseId, AssetSaveRequestDto assetSaveRequestDto) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(()->new IllegalArgumentException("해당 집이 없습니다. id = "+houseId));
        assetSaveRequestDto.setHouse(house);
        return assetRepository.save(assetSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, AssetUpdateRequestDto assetUpdateRequestDto) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 어셋이 없습니다. id = "+id));
        asset.update(assetUpdateRequestDto.getCategory(), assetUpdateRequestDto.getHouse(),
                assetUpdateRequestDto.getAssetName(), assetUpdateRequestDto.getProductName());
        return id;
    }
}
