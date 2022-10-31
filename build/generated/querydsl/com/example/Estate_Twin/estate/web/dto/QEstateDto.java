package com.example.Estate_Twin.estate.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.estate.web.dto.QEstateDto is a Querydsl Projection type for EstateDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QEstateDto extends ConstructorExpression<EstateDto> {

    private static final long serialVersionUID = 1535078956L;

    public QEstateDto(com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.estate.domain.entity.Estate> estate) {
        super(EstateDto.class, new Class<?>[]{com.example.Estate_Twin.estate.domain.entity.Estate.class}, estate);
    }

}

