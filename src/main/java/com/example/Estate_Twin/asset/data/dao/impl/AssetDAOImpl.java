package com.example.Estate_Twin.asset.data.dao.impl;

import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AssetDAOImpl implements AssetDAO {
    private AssetRepository assetRepository;

    @Override
    public Asset updateAsset(Long id, Category category, String assetName, String productName) {
        Asset selectedAsset = assetRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 에셋이 없습니다. id = "+id))
                .builder()
                .category(category)
                .assetName(assetName)
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
    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public Asset addAssetMedia(Long id, List<Media> mediaList) {
        Asset asset = findAsset(id);
        asset.addMedia(mediaList);
        return assetRepository.save(asset);
    }
}
