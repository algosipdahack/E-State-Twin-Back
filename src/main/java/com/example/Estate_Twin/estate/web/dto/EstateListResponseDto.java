package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class EstateListResponseDto {
    private final Long id;
    private final String estateThumbNail;
    private final String town;
    private final String buildingName;
    private final Long currentFloors;
    private final Long rentableArea;
    private final Long sellingFee;
    private final String state;
    @Schema(description = "매물 거래 유형", example = "MONTHLYRENT, LEASE, TRADING")
    private final String transactionType;
    @Schema(description = "매물 거래 종류", example = "APARTMENT, OFFICETELS")
    private final String estateType;

    @QueryProjection
    public EstateListResponseDto(Long id, TransactionType transactionType, String estateThumbNail, String town, EstateType estateType,
                                 String buildingName, Long currentFloors, Long rentableArea, String state, Long sellingFee) {
        this.id = id;
        this.transactionType = transactionType.toString();
        this.estateThumbNail = estateThumbNail;
        this.town = town;
        this.state = state;
        this.estateType = estateType.toString();
        this.buildingName = buildingName;
        this.currentFloors = currentFloors;
        this.rentableArea = rentableArea;
        this.sellingFee = sellingFee;
    }
}
