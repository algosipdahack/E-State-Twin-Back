package com.example.Estate_Twin.asset.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssetUpdateRequestDto {
    @NotNull
    @Schema(description = "에셋 카테고리", example = "AIRCONDITIONER, WASHER, BED, DESK, CLOSET, TV, REFRIGERATOR, SHOERACK, GASSTOVE, DOORLOCK, BIDET, INDUCTION, MICROWAVE, WALLPAPER, CURTAIN")
    private String category;
    @NotNull
    private String productName;
    @NotNull
    private String manufacturer;
    @NotNull
    private String anchorId;
    @NotNull
    private String assetPhoto;
}
