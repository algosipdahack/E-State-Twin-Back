package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class AssetUpdateRequestDto {
    private Category category;
    private String assetName;
    private String productName;
    private String manufacturer;
    private List<MediaSaveMultipartRequestDto> assetPhotos;

    @Builder
    public AssetUpdateRequestDto(Category category, String assetName, String productName, String manufacturer, List<MediaSaveMultipartRequestDto> assetPhotos) {
        this.category = category;
        this.assetName = assetName;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.assetPhotos = new ArrayList<>();
        assetPhotos.forEach(photo -> this.assetPhotos.add(photo));
    }
}
