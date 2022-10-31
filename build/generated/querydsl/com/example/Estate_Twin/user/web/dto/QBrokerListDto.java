package com.example.Estate_Twin.user.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.user.web.dto.QBrokerListDto is a Querydsl Projection type for BrokerListDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QBrokerListDto extends ConstructorExpression<BrokerListDto> {

    private static final long serialVersionUID = -778434240L;

    public QBrokerListDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> businessName, com.querydsl.core.types.Expression<Long> countOfTransactionCompletion, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.address.Address> address, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<String> brokerPhoto) {
        super(BrokerListDto.class, new Class<?>[]{long.class, String.class, long.class, String.class, com.example.Estate_Twin.address.Address.class, String.class, String.class}, id, businessName, countOfTransactionCompletion, content, address, phone, brokerPhoto);
    }

}

