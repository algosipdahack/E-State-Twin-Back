package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.querydsl.core.annotations.QueryProjection;

public class BrokerEstateDto {
    private Long id;
    private String userName; //집주인 이름
    private String phone;
    private Address address; //Estate address
    @QueryProjection
    public BrokerEstateDto(Estate estate) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
    }
}
