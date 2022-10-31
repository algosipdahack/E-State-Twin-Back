package com.example.Estate_Twin.estate.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.estate.web.dto.QBrokerEstateDto is a Querydsl Projection type for BrokerEstateDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QBrokerEstateDto extends ConstructorExpression<BrokerEstateDto> {

    private static final long serialVersionUID = 1651756947L;

    public QBrokerEstateDto(com.querydsl.core.types.Expression<Long> estateId, com.querydsl.core.types.Expression<String> ownerName, com.querydsl.core.types.Expression<String> ownerPhone, com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.address.Address> estateAddress) {
        super(BrokerEstateDto.class, new Class<?>[]{long.class, String.class, String.class, com.example.Estate_Twin.address.Address.class}, estateId, ownerName, ownerPhone, estateAddress);
    }

}

