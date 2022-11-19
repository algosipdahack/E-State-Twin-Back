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

    public QEstateModeDto(com.querydsl.core.types.Expression<Long> estateId, com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.address.Address> address, com.querydsl.core.types.Expression<com.example.Estate_Twin.estate.domain.entity.EstateType> estateType, com.querydsl.core.types.Expression<com.example.Estate_Twin.contractstate.domain.entity.State> state) {
        super(EstateModeDto.class, new Class<?>[]{long.class, com.example.Estate_Twin.address.Address.class, com.example.Estate_Twin.estate.domain.entity.EstateType.class, com.example.Estate_Twin.contractstate.domain.entity.State.class}, estateId, address, estateType, state);
    }

}

