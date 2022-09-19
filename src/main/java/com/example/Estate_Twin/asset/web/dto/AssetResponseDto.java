package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.media.web.dto.MediaResponseDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class AssetResponseDto {
    private final Long id;
    private final Category category;
    private final Set<MediaResponseDto> assetPhoto;
    private final String assetName;
    private final String productName;
    private final String manufacturer;

    //TODO 체크리스트 포함하기

    @QueryProjection
    public AssetResponseDto(Asset asset) {
        this.id = asset.getId();
        this.category = asset.getCategory();
        this.assetPhoto = new HashSet<>();
        asset.getAssetPhoto().forEach(photo -> assetPhoto.add(new MediaResponseDto(photo)));
        this.assetName = asset.getAssetName();
        this.productName = asset.getProductName();
        this.manufacturer = asset.getManufacturer();
    }
}
