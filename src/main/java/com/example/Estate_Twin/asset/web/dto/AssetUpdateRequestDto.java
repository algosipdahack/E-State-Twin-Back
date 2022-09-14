package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.checklist.data.entity.Category;
import lombok.*;

@Getter
@NoArgsConstructor
public class AssetUpdateRequestDto {
    private Category category;
    private String assetName;
    private String productName;
    private String manufacturer;

    @Builder
    public AssetUpdateRequestDto(Category category, String assetName, String productName, String manufacturer) {
        this.category = category;
        this.assetName = assetName;
        this.productName = productName;
        this.manufacturer = manufacturer;
    }
}
