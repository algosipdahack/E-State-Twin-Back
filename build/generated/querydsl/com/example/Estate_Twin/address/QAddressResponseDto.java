package com.example.Estate_Twin.address;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.example.Estate_Twin.address.QAddressResponseDto is a Querydsl Projection type for AddressResponseDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QAddressResponseDto extends ConstructorExpression<AddressResponseDto> {

    private static final long serialVersionUID = 1130974344L;

    public QAddressResponseDto(com.querydsl.core.types.Expression<? extends Address> address) {
        super(AddressResponseDto.class, new Class<?>[]{Address.class}, address);
    }

}

