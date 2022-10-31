package com.example.Estate_Twin.estate.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.estate.web.dto.QEstateListResponseDto is a Querydsl Projection type for EstateListResponseDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QEstateListResponseDto extends ConstructorExpression<EstateListResponseDto> {

    private static final long serialVersionUID = -1898909907L;

    public QEstateListResponseDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<com.example.Estate_Twin.estate.domain.entity.TransactionType> transactionType, com.querydsl.core.types.Expression<String> estateThumbNail, com.querydsl.core.types.Expression<String> town, com.querydsl.core.types.Expression<com.example.Estate_Twin.estate.domain.entity.EstateType> estateType, com.querydsl.core.types.Expression<String> buildingName, com.querydsl.core.types.Expression<Long> currentFloors, com.querydsl.core.types.Expression<Long> rentableArea, com.querydsl.core.types.Expression<com.example.Estate_Twin.contractstate.domain.entity.State> state, com.querydsl.core.types.Expression<Long> sellingFee) {
        super(EstateListResponseDto.class, new Class<?>[]{long.class, com.example.Estate_Twin.estate.domain.entity.TransactionType.class, String.class, String.class, com.example.Estate_Twin.estate.domain.entity.EstateType.class, String.class, long.class, long.class, com.example.Estate_Twin.contractstate.domain.entity.State.class, long.class}, id, transactionType, estateThumbNail, town, estateType, buildingName, currentFloors, rentableArea, state, sellingFee);
    }

}

