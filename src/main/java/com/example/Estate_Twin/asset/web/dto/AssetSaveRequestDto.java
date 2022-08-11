package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.house.domain.House;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AssetSaveRequestDto {
    private House house;

    private String category;

    private String assetName;

    private String productName;

    @Builder
    public AssetSaveRequestDto(House house, String category, String assetName, String productName) {
        this.house = house;
        this.category = category;
        this.assetName = assetName;
        this.productName = productName;
    }

    public Asset toEntity() {
        return Asset.builder()
                .assetName(assetName)
                .category(category)
                .house(house)
                .productName(productName)
                .build();
    }
}
