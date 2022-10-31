package com.example.Estate_Twin.estate.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.estate.web.dto.QEstateModeDto is a Querydsl Projection type for EstateModeDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QEstateModeDto extends ConstructorExpression<EstateModeDto> {

    private static final long serialVersionUID = -1610200311L;

    public QEstateModeDto(com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.address.Address> address, com.querydsl.core.types.Expression<com.example.Estate_Twin.estate.domain.entity.EstateType> estateType) {
        super(EstateModeDto.class, new Class<?>[]{com.example.Estate_Twin.address.Address.class, com.example.Estate_Twin.estate.domain.entity.EstateType.class}, address, estateType);
    }

}

