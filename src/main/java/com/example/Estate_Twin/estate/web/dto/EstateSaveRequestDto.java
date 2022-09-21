package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class EstateSaveRequestDto {
    private String estateThumbNail;
    private String content;
    @Schema(description = "매물 거래 유형", example = "MONTHLYRENT, LEASE, TRADING")
    private String transactionType;
    //2차원 도면 사진
    private MediaSaveMultipartRequestDto model;
    private MediaSaveMultipartRequestDto arCam;
    private AddressSaveRequestDto address;
    private HouseSaveRequestDto house;
    private List<MediaSaveMultipartRequestDto> estatePhotos;
    private List<AssetSaveRequestDto> assetSaveRequestDtos;

    public Estate toEntity() {
        String city = address.getCity();
        String borough = address.getBorough();
        String town = address.getTown();
        TransactionType type = TransactionType.of(transactionType);
        return Estate.builder()
                .content(content)
                .estateThumbNail(estateThumbNail)
                .transactionType(type)
                .town(town)
                .city(city)
                .borough(borough)
                .build();
    }

}
