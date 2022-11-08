package com.example.Estate_Twin.estate.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.estate.web.dto.QEstateDetailDto is a Querydsl Projection type for EstateDetailDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QEstateDetailDto extends ConstructorExpression<EstateDetailDto> {

    private static final long serialVersionUID = -1140750757L;

    public QEstateDetailDto(com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.estate.domain.entity.Estate> estate, com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.house.domain.entity.House> house, com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.user.domain.entity.Broker> broker, com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.estate.domain.entity.EstateHit> estateHit, com.querydsl.core.types.Expression<? extends java.util.List<com.example.Estate_Twin.asset.data.entity.Asset>> assets) {
        super(EstateDetailDto.class, new Class<?>[]{com.example.Estate_Twin.estate.domain.entity.Estate.class, com.example.Estate_Twin.house.domain.entity.House.class, com.example.Estate_Twin.user.domain.entity.Broker.class, com.example.Estate_Twin.estate.domain.entity.EstateHit.class, java.util.List.class}, estate, house, broker, estateHit, assets);
    }

}

