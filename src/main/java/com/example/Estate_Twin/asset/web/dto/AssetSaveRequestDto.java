package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssetSaveRequestDto {
    @NotNull
    @Schema(description = "에셋 큰 카테고리", example = "HOMEAPPLIANCES, FURNITURE, BATHROOM, INTERIOR")
    private String category;
    @NotNull
    private String productName;
    @NotNull
    private String manufacturer;
    private String anchorId;
    @NotNull
    private String assetPhoto;

    public Asset toEntity() {
        return Asset.builder()
                .category(Category.of(category))
                .productName(productName)
                .manufacturer(manufacturer)
                .anchorId(anchorId)
                .assetPhoto(assetPhoto)
                .build();
    }
}
