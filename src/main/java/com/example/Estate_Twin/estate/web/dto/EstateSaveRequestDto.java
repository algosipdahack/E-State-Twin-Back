package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class EstateSaveRequestDto {
    private TransactionType transactionType;
    private String estateThumbNail;
    private String content;
    private String city;
    private String borough;
    private String town;
    private String model;
    private List<MediaSaveMultipartRequestDto> estatePhotos;
    private AddressSaveRequestDto address;
    private HouseSaveRequestDto house;
    private List<AssetSaveRequestDto> assetSaveRequestDtos;

    @Builder
    public EstateSaveRequestDto(String transactionType, String model, List<MediaSaveMultipartRequestDto> estatePhotos,
                                String estateThumbNail, String content, HouseSaveRequestDto house,
                                AddressSaveRequestDto address, List<AssetSaveRequestDto> assets) {
        this.transactionType = TransactionType.of(transactionType);
        this.model = model;
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.address = address;
        this.house = house;
        this.assetSaveRequestDtos = new ArrayList<>();
        assets.forEach(asset -> this.assetSaveRequestDtos.add(asset));
        this.estatePhotos = new ArrayList<>();
        estatePhotos.forEach(multipartFile -> this.estatePhotos.add(multipartFile));
        this.city = address.getCity();
        this.borough = address.getBorough();
        this.town = address.getTown();
    }

    public Estate toEntity() {
        return Estate.builder()
                .content(content)
                .estateThumbNail(estateThumbNail)
                .model(model)
                .transactionType(transactionType)
                .town(town)
                .city(city)
                .borough(borough)
                .build();
    }

}
