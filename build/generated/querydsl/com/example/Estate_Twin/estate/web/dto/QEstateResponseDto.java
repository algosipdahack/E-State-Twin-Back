package com.example.Estate_Twin.estate.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.estate.web.dto.QEstateResponseDto is a Querydsl Projection type for EstateResponseDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QEstateResponseDto extends ConstructorExpression<EstateResponseDto> {

    private static final long serialVersionUID = -1417259733L;

    public QEstateResponseDto(com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.estate.domain.entity.Estate> estate, com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.house.domain.entity.House> house, com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.estate.domain.entity.EstateHit> estateHit, com.querydsl.core.types.Expression<? extends java.util.List<com.example.Estate_Twin.asset.data.entity.Asset>> assets, com.querydsl.core.types.Expression<? extends java.util.List<com.example.Estate_Twin.checklist.data.entity.CheckList>> checkLists) {
        super(EstateResponseDto.class, new Class<?>[]{com.example.Estate_Twin.estate.domain.entity.Estate.class, com.example.Estate_Twin.house.domain.entity.House.class, com.example.Estate_Twin.estate.domain.entity.EstateHit.class, java.util.List.class, java.util.List.class}, estate, house, estateHit, assets, checkLists);
    }

}

