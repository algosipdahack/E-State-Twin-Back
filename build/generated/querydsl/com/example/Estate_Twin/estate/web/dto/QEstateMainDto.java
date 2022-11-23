package com.example.Estate_Twin.estate.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.estate.web.dto.QEstateMainDto is a Querydsl Projection type for EstateMainDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QEstateMainDto extends ConstructorExpression<EstateMainDto> {

    private static final long serialVersionUID = -2006122701L;

    public QEstateMainDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> userBorough, com.querydsl.core.types.Expression<String> estateThumbNail, com.querydsl.core.types.Expression<String> town, com.querydsl.core.types.Expression<String> thumbnail3D, com.querydsl.core.types.Expression<com.example.Estate_Twin.estate.domain.entity.TransactionType> transactionType, com.querydsl.core.types.Expression<Long> sellingFee, com.querydsl.core.types.Expression<com.example.Estate_Twin.estate.domain.entity.EstateType> estateType, com.querydsl.core.types.Expression<Long> deposit, com.querydsl.core.types.Expression<Long> monthlyRent) {
        super(EstateMainDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, com.example.Estate_Twin.estate.domain.entity.TransactionType.class, long.class, com.example.Estate_Twin.estate.domain.entity.EstateType.class, long.class, long.class}, id, userBorough, estateThumbNail, town, thumbnail3D, transactionType, sellingFee, estateType, deposit, monthlyRent);
    }

}

