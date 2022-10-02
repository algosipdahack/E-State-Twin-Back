package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.Address;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

//브로커가 가진 estate중 특정 상태 보여주기
@Getter
public class BrokerEstateDto {
    private Long estateId;
    private String ownerName; //집주인 이름
    private String ownerPhone;
    private Address estateAddress; //Estate address
    @QueryProjection
    public BrokerEstateDto(Long estateId, String ownerName, String ownerPhone, Address estateAddress) {
        this.estateId = estateId;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.estateAddress = estateAddress;
    }
}
