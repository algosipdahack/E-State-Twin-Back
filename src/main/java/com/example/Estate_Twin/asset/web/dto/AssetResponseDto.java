package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.house.domain.House;
import com.example.Estate_Twin.media.domain.Media;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AssetResponseDto {

    private final Long id;

    private final House house;

    private final String category;

    private final List<Media> assetPhoto;

    private final String assetName;

    private final String productName;

    public AssetResponseDto(Asset asset) {
        this.id = asset.getId();
        this.house = asset.getHouse();
        this.category = asset.getCategory();
        this.assetPhoto = asset.getAssetPhoto();
        this.assetName = asset.getAssetName();
        this.productName = asset.getProductName();
    }
}
