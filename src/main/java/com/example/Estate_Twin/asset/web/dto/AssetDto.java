package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.web.dto.HouseResponseDto;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.Getter;

import java.util.List;
@Getter
public class AssetDto {
    private final Long id;
    private final HouseResponseDto house;
    private final Category category;
    private final List<Media> assetPhoto;
    private final String assetName;
    private final String productName;

    public AssetDto(Asset asset) {
        this.id = asset.getId();
        this.house = new HouseResponseDto(asset.getHouse());
        this.category = asset.getCategory();
        this.assetPhoto = asset.getAssetPhoto();
        this.assetName = asset.getAssetName();
        this.productName = asset.getProductName();
    }
}
