package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;
import lombok.Getter;

@Getter
public class EstateListResponseDto {
    private final TransactionType transactionType;
    private final String estateThumbNail;
    private final String town;
    private final EstateType estateType;
    private final String buildingName;
    private final Long currentFloors;
    private final Long rentableArea;

    public EstateListResponseDto(Estate estate) {
        House house = estate.getHouse();
        this.transactionType = estate.getTransactionType();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.town = estate.getTown();
        this.estateType = house.getEstateType();
        this.buildingName = estate.getAddress().getBuildingName();
        this.currentFloors = house.getCurrentFloors();
        this.rentableArea = house.getRentableArea();
    }
}
