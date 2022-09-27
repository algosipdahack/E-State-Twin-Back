package com.example.Estate_Twin.asset.data.dao;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.asset.data.entity.Option;
import com.example.Estate_Twin.estate.domain.entity.Estate;


public interface AssetDAO {
    Asset updateAsset(Long id, Category category, Option option, String productName);
    Asset findAsset(Long id);
    //Asset findAssetUtilCheckList(Long id);
    Asset saveAsset(Estate estate, Asset asset);
}
