package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.asset.data.entity.Option;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class AssetUpdateRequestDto {
    private Category category;
    private Option option;
    private String productName;
    private String manufacturer;
    private List<MediaSaveMultipartRequestDto> assetPhotos;

    @Builder
    public AssetUpdateRequestDto(Category category, Option option, String productName, String manufacturer, List<MediaSaveMultipartRequestDto> assetPhotos) {
        this.category = category;
        this.option = option;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.assetPhotos = new ArrayList<>();
        assetPhotos.forEach(photo -> this.assetPhotos.add(photo));
    }
}
