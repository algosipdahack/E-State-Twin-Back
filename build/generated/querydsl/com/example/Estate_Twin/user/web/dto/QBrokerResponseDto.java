package com.example.Estate_Twin.user.web.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.user.web.dto.QBrokerResponseDto is a Querydsl Projection type for BrokerResponseDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QBrokerResponseDto extends ConstructorExpression<BrokerResponseDto> {

    private static final long serialVersionUID = -595896515L;

    public QBrokerResponseDto(com.querydsl.core.types.Expression<? extends com.example.Estate_Twin.user.domain.entity.Broker> broker) {
        super(BrokerResponseDto.class, new Class<?>[]{com.example.Estate_Twin.user.domain.entity.Broker.class}, broker);
    }

}

