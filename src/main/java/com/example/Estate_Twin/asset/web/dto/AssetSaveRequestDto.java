package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.*;

@Getter
@NoArgsConstructor
public class AssetSaveRequestDto {
    @Schema(description = "에셋 큰 카테고리", example = "HOMEAPPLIANCES, FURNITURE, BATHROOM, INTERIOR")
    private String category;
    @Schema(description = "에셋 작은 카테고리(옵션)", example = "AIRCONDITIONER, WASHER, BED, DESK, CLOSET, TV, REFRIGERATOR, SHOERACK, GASSTOVE, DOORLOCK, BIDET, WALLPAPER, CURTAIN")
    private String option;
    private String productName;
    private String manufacturer;
    private List<MediaSaveMultipartRequestDto> assetPhotos;

    public Asset toEntity() {
        return Asset.builder()
                .option(Option.of(option))
                .category(Category.of(category))
                .productName(productName)
                .manufacturer(manufacturer)
                .build();
    }
}
