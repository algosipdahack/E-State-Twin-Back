package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.asset.data.entity.Option;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class AssetSaveRequestDto {
    private Category category;
    private Option option;
    private String productName;
    private String manufacturer;
    private List<MediaSaveMultipartRequestDto> assetPhotos;

    @Builder
    public AssetSaveRequestDto(String category, String option, String productName, String manufacturer, List<MediaSaveMultipartRequestDto> assetPhotos) {
        this.category = Category.of(category);
        this.option = Option.of(option);
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.assetPhotos = new ArrayList<>();
        assetPhotos.forEach(assetPhoto -> this.assetPhotos.add(assetPhoto));
    }

    public Asset toEntity() {
        return Asset.builder()
                .option(option)
                .category(category)
                .productName(productName)
                .manufacturer(manufacturer)
                .build();
    }
}
