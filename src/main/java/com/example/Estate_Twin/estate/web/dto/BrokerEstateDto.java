package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.querydsl.core.annotations.QueryProjection;

public class BrokerEstateDto {
    private Long id;
    private String userName;
    private String phone;
    private AddressDto address;
    @QueryProjection
    public BrokerEstateDto(Long id, String userName, String phone) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
    }
    public void setAddress(Address address) {
        this.address = new AddressDto(address);
    }

}
