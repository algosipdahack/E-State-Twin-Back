package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;
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
                                 String buildingName, Long currentFloors, Long rentableArea, State state, Long sellingFee) {
        this.id = id;
        this.transactionType = transactionType.toString();
        this.estateThumbNail = estateThumbNail;
        this.town = town;
        this.state = state.toString();
        this.estateType = estateType.toString();
        this.buildingName = buildingName;
        this.currentFloors = currentFloors;
        this.rentableArea = rentableArea;
        this.sellingFee = sellingFee;
    }

    public EstateListResponseDto(Estate estate, House house) {
        this.id = estate.getId();
        this.transactionType = estate.getTransactionType().toString();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.town = estate.getAddress().getTown();
        this.state = estate.getState().toString();
        this.estateType = house.getEstateType().toString();
        this.buildingName = estate.getAddress().getBuildingName();
        this.currentFloors = house.getCurrentFloors();
        this.rentableArea = house.getRentableArea();
        this.sellingFee = house.getSellingFee();
    }
}
