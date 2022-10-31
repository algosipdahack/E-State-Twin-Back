package com.example.Estate_Twin.estate.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.estate.web.dto.QEstateHitDto is a Querydsl Projection type for EstateHitDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QEstateHitDto extends ConstructorExpression<EstateHitDto> {

    private static final long serialVersionUID = -1170013729L;

    public QEstateHitDto(com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.estate.domain.entity.EstateHit> estateHit) {
        super(EstateHitDto.class, new Class<?>[]{com.example.Estate_Twin.estate.domain.entity.EstateHit.class}, estateHit);
    }

}

