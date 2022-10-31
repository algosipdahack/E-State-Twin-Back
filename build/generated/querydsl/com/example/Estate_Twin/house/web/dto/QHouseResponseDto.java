package com.example.Estate_Twin.house.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.house.web.dto.QHouseResponseDto is a Querydsl Projection type for HouseResponseDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QHouseResponseDto extends ConstructorExpression<HouseResponseDto> {

    private static final long serialVersionUID = 137786681L;

    public QHouseResponseDto(com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.house.domain.entity.House> house) {
        super(HouseResponseDto.class, new Class<?>[]{com.example.Estate_Twin.house.domain.entity.House.class}, house);
    }

}

