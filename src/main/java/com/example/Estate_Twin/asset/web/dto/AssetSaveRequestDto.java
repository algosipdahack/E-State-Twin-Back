package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.house.domain.entity.House;
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
    public AssetSaveRequestDto(String category, String assetName, String productName) {
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

    public void setHouse(House house) {
        this.house = house;
    }
}
