package com.example.Estate_Twin.asset.data.dao;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.media.domain.entity.Media;


public interface AssetDAO {
    Asset updateAsset(Long id, Category category, String assetName, String productName);
    Asset findAsset(Long id);
    Asset saveAsset(Asset asset);
    Asset addAssetMedia(Long id, Media media);
    void clearMedia(Long assetId);
}
