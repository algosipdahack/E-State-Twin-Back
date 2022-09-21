package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.media.web.dto.MediaResponseDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.*;

@Getter
public class AssetDto {
    private final Category category;
    private final Set<MediaResponseDto> assetPhotos;
    private final Option option;
    private final String productName;
    private final String manufacturer;
    private final String anchorId;

    @QueryProjection
    public AssetDto(Asset asset) {
        this.category = asset.getCategory();
        this.assetPhotos = new HashSet<>();
        asset.getAssetPhoto().forEach(photo -> assetPhotos.add(new MediaResponseDto(photo)));
        this.option = asset.getOption();
        this.productName = asset.getProductName();
        this.manufacturer = asset.getManufacturer();
        this.anchorId = asset.getAnchorId();
    }
}
