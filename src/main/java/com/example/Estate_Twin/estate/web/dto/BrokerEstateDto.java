package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.user.domain.entity.User;
import com.querydsl.core.annotations.QueryProjection;

//브로커가 가진 estate중 특정 상태 보여주기
public class BrokerEstateDto {
    private Long estateId;
    private String ownerName; //집주인 이름
    private String ownerPhone;
    private Address estateAddress; //Estate address
    @QueryProjection
    public BrokerEstateDto(Estate estate, User owner) {
        this.estateId = estate.getId();
        this.ownerName = owner.getName();
        this.ownerPhone = owner.getPhone();
        this.estateAddress = estate.getAddress();
    }
}
