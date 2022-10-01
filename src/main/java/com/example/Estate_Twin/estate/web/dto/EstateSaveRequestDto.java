package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;

@Getter
@NoArgsConstructor
public class EstateSaveRequestDto {
    private Long id;
    private String estateThumbNail;
    private String content;
    @Schema(description = "매물 거래 유형", example = "MONTHLYRENT, LEASE, TRADING")
    private String transactionType;
    //2차원 도면 사진
    private String model;
    private String arCam;
    private HouseSaveRequestDto house;
    private List<String> estatePhotos;
    private List<AssetSaveRequestDto> assets;
}
