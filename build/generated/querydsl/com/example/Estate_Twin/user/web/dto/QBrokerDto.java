package com.example.Estate_Twin.user.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.user.web.dto.QBrokerDto is a Querydsl Projection type for BrokerDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QBrokerDto extends ConstructorExpression<BrokerDto> {

    private static final long serialVersionUID = -501701058L;

    public QBrokerDto(com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.user.domain.entity.Broker> broker) {
        super(BrokerDto.class, new Class<?>[]{com.example.Estate_Twin.user.domain.entity.Broker.class}, broker);
    }

}

