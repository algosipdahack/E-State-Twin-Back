package com.example.Estate_Twin.asset.web.dto;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class AssetSaveRequestDto {
    private Category category;
    private String assetName;
    private String productName;
    private String manufacturer;
    @Schema()
    private List<MultipartFile> assetPhotos;

    @Builder
    public AssetSaveRequestDto(Category category, String assetName, String productName, String manufacturer, List<MultipartFile> assetPhotos) {
        this.category = category;
        this.assetName = assetName;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.assetPhotos = new ArrayList<>();
        assetPhotos.forEach(assetPhoto -> this.assetPhotos.add(assetPhoto));
    }

    public Asset toEntity() {
        return Asset.builder()
                .assetName(assetName)
                .category(category)
                .productName(productName)
                .manufacturer(manufacturer)
                .build();
    }
}
