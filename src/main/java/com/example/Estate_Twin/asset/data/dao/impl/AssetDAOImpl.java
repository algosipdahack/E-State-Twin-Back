package com.example.Estate_Twin.asset.data.dao.impl;

import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.asset.web.dto.AssetUpdateRequestDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@AllArgsConstructor
@Transactional(readOnly = true)
public class AssetDAOImpl implements AssetDAO {
    private AssetRepository assetRepository;

    // checklist까지 정보 가져옴
    @Override
    public Asset findAsset(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(()-> new CheckHouseException(ErrorCode.ASSET_NOT_FOUND));
    }

    @Transactional
    @Override
    public Asset updateAsset(Long id, AssetUpdateRequestDto updateRequestDto) {
        return findAsset(id).update(updateRequestDto);
    }

    @Override
    @Transactional
    public Asset saveAsset(Estate estate, Asset asset) {
        asset.setEstate(estate);
        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> findAssetsByEstateId(Long estateId) {
        return assetRepository.findAssetsByEstate_Id(estateId).orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND));
    }
}
