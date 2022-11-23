package com.example.Estate_Twin.estate.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.estate.web.dto.QEstateOwnerDto is a Querydsl Projection type for EstateOwnerDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QEstateOwnerDto extends ConstructorExpression<EstateOwnerDto> {

    private static final long serialVersionUID = -388237825L;

    public QEstateOwnerDto(com.querydsl.core.types.Expression<Long> estateId, com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.address.Address> address, com.querydsl.core.types.Expression<com.example.Estate_Twin.contractstate.domain.entity.State> state) {
        super(EstateOwnerDto.class, new Class<?>[]{long.class, com.example.Estate_Twin.address.Address.class, com.example.Estate_Twin.contractstate.domain.entity.State.class}, estateId, address, state);
    }

}

