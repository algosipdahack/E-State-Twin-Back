package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.querydsl.core.annotations.QueryProjection;
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

    @QueryProjection
    public EstateListResponseDto(Long id,TransactionType transactionType,String estateThumbNail,String town,EstateType estateType,
                                 String buildingName,Long currentFloors,Long rentableArea,String state) {
        this.id = id;
        this.transactionType = transactionType;
        this.estateThumbNail = estateThumbNail;
        this.town = town;
        this.state = state;
        this.estateType = estateType;
        this.buildingName = buildingName;
        this.currentFloors = currentFloors;
        this.rentableArea = rentableArea;
    }
}
