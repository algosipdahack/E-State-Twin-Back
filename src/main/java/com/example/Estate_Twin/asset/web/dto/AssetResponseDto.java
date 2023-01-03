package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
public class AssetResponseDto {
    private final Long id;
    @Schema(description = "에셋 카테고리", example = "AIRCONDITIONER, WASHER, BED, DESK, CLOSET, TV, REFRIGERATOR, SHOERACK, GASSTOVE, DOORLOCK, BIDET, INDUCTION, MICROWAVE, WALLPAPER, CURTAIN")
    private final String category;
    private final String assetPhoto;
    private final String productName;
    private final String manufacturer;
    private final String anchorId;
    private final List<CheckListResponseDto> checkLists;

    @Builder
    @QueryProjection
    public AssetResponseDto(Asset asset) {
        this.id = asset.getId();
        this.category = asset.getCategory().toString();
        this.assetPhoto = asset.getAssetPhoto();
        this.productName = asset.getProductName();
        this.manufacturer = asset.getManufacturer();
        this.anchorId = asset.getAnchorId();
        this.checkLists = new ArrayList<>();
        asset.getCheckLists().forEach(checkList -> this.checkLists.add(new CheckListResponseDto(checkList)));
    }

}
