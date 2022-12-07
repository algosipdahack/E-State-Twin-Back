package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class AssetSummaryDto {
    private final Long id;
    @Schema(description = "에셋 큰 카테고리", example = "HOMEAPPLIANCES, FURNITURE, BATHROOM, INTERIOR")
    private final String category;
    private final String assetPhoto;
    private final String productName;
    private final String manufacturer;
    private final String anchorId;

    @QueryProjection
    public AssetSummaryDto(Asset asset) {
        this.id = asset.getId();
        this.category = asset.getCategory().toString();
        this.assetPhoto = asset.getAssetPhoto();
        this.productName = asset.getProductName();
        this.manufacturer = asset.getManufacturer();
        this.anchorId = asset.getAnchorId();
    }
}
