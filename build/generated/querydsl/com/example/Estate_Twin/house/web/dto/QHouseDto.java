package com.example.Estate_Twin.house.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.house.web.dto.QHouseDto is a Querydsl Projection type for HouseDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QHouseDto extends ConstructorExpression<HouseDto> {

    private static final long serialVersionUID = -1101037510L;

    public QHouseDto(com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.house.domain.entity.House> house) {
        super(HouseDto.class, new Class<?>[]{com.example.Estate_Twin.house.domain.entity.House.class}, house);
    }

}

