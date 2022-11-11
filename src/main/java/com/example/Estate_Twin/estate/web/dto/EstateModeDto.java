package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class EstateModeDto {
    private final Address address;
    private final String estateType;
    private final State state;

    @QueryProjection
    public EstateModeDto(Address address, EstateType estateType, State state) {
        this.address = address;
        this.estateType = estateType.toString();
        this.state = state;
    }
}
