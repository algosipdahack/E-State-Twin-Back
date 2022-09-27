package com.example.Estate_Twin.asset.data.dao.impl;

import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class AssetDAOImpl implements AssetDAO {
    private AssetRepository assetRepository;

    @Override
    public Asset updateAsset(Long id, Category category, Option option, String productName) {
        Asset selectedAsset = assetRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 에셋이 없습니다. id = "+id))
                .builder()
                .category(category)
                .option(option)
                .productName(productName)
                .build();
        return assetRepository.save(selectedAsset);
    }

    @Override
    public Asset findAsset(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 에셋이 없습니다. id = "+id));
    }

    @Override
    public Asset saveAsset(Estate estate, Asset asset) {
        asset.setEstate(estate);
        return assetRepository.save(asset);
    }
}
