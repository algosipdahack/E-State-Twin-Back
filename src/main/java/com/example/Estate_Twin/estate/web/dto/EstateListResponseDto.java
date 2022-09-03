package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;
import lombok.Getter;

@Getter
public class EstateListResponseDto {
    private final Long id;
    private final TransactionType transactionType;
    private final String estateThumbNail;
    private final String town;
    private final EstateType estateType;
    private final String buildingName;
    private final Long currentFloors;
    private final Long rentableArea;
    private final String state;

    public EstateListResponseDto(Estate estate) {
        this.id = estate.getId();
        House house = estate.getHouse();
        this.transactionType = estate.getTransactionType();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.town = estate.getTown();
        this.state = estate.getState().toString();
        this.estateType = house.getEstateType();
        this.buildingName = estate.getAddress().getBuildingName();
        this.currentFloors = house.getCurrentFloors();
        this.rentableArea = house.getRentableArea();
    }
}
