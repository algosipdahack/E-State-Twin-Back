package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.house.domain.entity.House;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AssetUpdateRequestDto {
    private House house;

    private Category category;

    private String assetName;

    private String productName;

    @Builder
    public AssetUpdateRequestDto(House house, Category category, String assetName, String productName) {
        this.house = house;
        this.category = category;
        this.assetName = assetName;
        this.productName = productName;
    }
}
