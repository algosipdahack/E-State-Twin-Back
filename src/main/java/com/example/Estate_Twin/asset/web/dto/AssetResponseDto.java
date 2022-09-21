package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.media.web.dto.MediaResponseDto;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.*;

@Getter
public class AssetResponseDto {
    private final Long id;
    @Schema(description = "에셋 큰 카테고리", example = "HOMEAPPLIANCES, FURNITURE, BATHROOM, INTERIOR")
    private final String category;
    private final Set<MediaResponseDto> assetPhoto;
    @Schema(description = "에셋 작은 카테고리(옵션)", example = "AIRCONDITIONER, WASHER, BED, DESK, CLOSET, TV, REFRIGERATOR, SHOERACK, GASSTOVE, DOORLOCK, BIDET, WALLPAPER, CURTAIN")
    private final String option;
    private final String productName;
    private final String manufacturer;
    private final String anchorId;
    //TODO 체크리스트 포함하기

    @QueryProjection
    public AssetResponseDto(Asset asset) {
        this.id = asset.getId();
        this.category = asset.getCategory().toString();
        this.assetPhoto = new HashSet<>();
        asset.getAssetPhoto().forEach(photo -> assetPhoto.add(new MediaResponseDto(photo)));
        this.option = asset.getOption().toString();
        this.productName = asset.getProductName();
        this.manufacturer = asset.getManufacturer();
        this.anchorId = asset.getAnchorId();
    }
}
