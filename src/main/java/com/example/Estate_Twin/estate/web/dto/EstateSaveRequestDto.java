package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EstateSaveRequestDto {
    private Long id;
    @Schema(description = "매물 거래 유형", example = "MONTHLYRENT, LEASE, TRADING")
    private String transactionType;
    //2차원 도면 사진
    private String floorplan;
    private String arCam;
    private HouseSaveRequestDto house;
    private List<String> estatePhotos;
    private List<String> estateVideos;
    private List<AssetSaveRequestDto> assets;
}
