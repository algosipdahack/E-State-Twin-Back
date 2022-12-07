package com.example.Estate_Twin.asset.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssetUpdateRequestDto {
    @NotNull
    @Schema(description = "에셋 큰 카테고리", example = "HOMEAPPLIANCES, FURNITURE, BATHROOM, INTERIOR")
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
