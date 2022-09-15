package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.Getter;

import java.util.List;
@Getter
public class AssetDto {
    private final EstateResponseDto estate;
    private final Category category;
    private final List<Media> assetPhoto;
    private final String assetName;
    private final String productName;
    private final String manufacturer;

    public AssetDto(Asset asset) {
        this.estate = new EstateResponseDto(asset.getEstate());
        this.category = asset.getCategory();
        this.assetPhoto = asset.getAssetPhoto();
        this.assetName = asset.getAssetName();
        this.productName = asset.getProductName();
        this.manufacturer = asset.getManufacturer();
    }
}
