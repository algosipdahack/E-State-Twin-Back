package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.house.domain.entity.House;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AssetUpdateRequestDto {
    private House house;

    private String category;

    private String assetName;

    private String productName;

    @Builder
    public AssetUpdateRequestDto(House house, String category, String assetName, String productName) {
        this.house = house;
        this.category = category;
        this.assetName = assetName;
        this.productName = productName;
    }
}
