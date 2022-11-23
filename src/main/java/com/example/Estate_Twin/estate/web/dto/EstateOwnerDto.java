package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
public class EstateOwnerDto {
    private final Long estateId;
    private final Address address;
    private final State state;

    @Builder
    @QueryProjection
    public EstateOwnerDto(Long estateId, Address address, State state) {
        this.estateId = estateId;
        this.address = address;
        this.state = state;
    }
}
