package com.example.Estate_Twin.asset.service;

import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.asset.domain.repository.AssetRepository;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.asset.web.dto.AssetUpdateRequestDto;
import com.example.Estate_Twin.checklist.domain.CheckList;
import com.example.Estate_Twin.checklist.domain.repository.CheckListRepository;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListSaveRequestDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;
    public AssetResponseDto findById(Long id) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 어셋이 없습니다. id = "+id));
        return new AssetResponseDto(asset);
    }

    @Transactional
    public Long save(AssetSaveRequestDto assetSaveRequestDto) {
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
