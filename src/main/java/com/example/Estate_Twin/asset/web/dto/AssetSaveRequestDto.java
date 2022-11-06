package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
public class AssetSaveRequestDto {
    @Schema(description = "에셋 큰 카테고리", example = "HOMEAPPLIANCES, FURNITURE, BATHROOM, INTERIOR")
    private String category;
    @Schema(description = "에셋 작은 카테고리(옵션)", example = "AIRCONDITIONER, WASHER, BED, DESK, CLOSET, TV, REFRIGERATOR, SHOERACK, GASSTOVE, DOORLOCK, BIDET, INDUCTION, MICROWAVE, WALLPAPER, CURTAIN")
    private String option;
    private String productName;
    private String manufacturer;
    private String anchorId;
    private String assetPhoto;

    public Asset toEntity() {
        return Asset.builder()
                .option(Option.of(option))
                .category(Category.of(category))
                .productName(productName)
                .manufacturer(manufacturer)
                .anchorId(anchorId)
                .assetPhoto(assetPhoto)
                .build();
    }
}
