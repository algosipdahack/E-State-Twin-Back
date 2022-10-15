package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.Address;
import com.querydsl.core.annotations.QueryProjection;

public class EstateModeDto {
    private final Address address;
    private final boolean isOfficetel;

    @QueryProjection
    public EstateModeDto(Address address, boolean isOfficetel) {
        this.address = address;
        this.isOfficetel = isOfficetel;
    }
}
