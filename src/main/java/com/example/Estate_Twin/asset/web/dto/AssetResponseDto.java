package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.asset.data.entity.Option;
import com.example.Estate_Twin.media.web.dto.MediaResponseDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class AssetResponseDto {
    private final Long id;
    private final Category category;
    private final Set<MediaResponseDto> assetPhoto;
    private final Option option;
    private final String productName;
    private final String manufacturer;
    private final String anchorId;
    //TODO 체크리스트 포함하기

    @QueryProjection
    public AssetResponseDto(Asset asset) {
        this.id = asset.getId();
        this.category = asset.getCategory();
        this.assetPhoto = new HashSet<>();
        asset.getAssetPhoto().forEach(photo -> assetPhoto.add(new MediaResponseDto(photo)));
        this.option = asset.getOption();
        this.productName = asset.getProductName();
        this.manufacturer = asset.getManufacturer();
        this.anchorId = asset.getAnchorId();
    }
}
