package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.Category;
import lombok.*;

@Getter
@NoArgsConstructor
public class AssetSaveRequestDto {
    private Category category;
    private String assetName;
    private String productName;

    @Builder
    public AssetSaveRequestDto(Category category, String assetName, String productName) {
        this.category = category;
        this.assetName = assetName;
        this.productName = productName;
    }

    public Asset toEntity() {
        return Asset.builder()
                .assetName(assetName)
                .category(category)
                .productName(productName)
                .build();
    }
}
