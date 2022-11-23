package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import lombok.*;
// 집주인 모드에서 사용
@Getter
public class EstateOwnerDto {
    private final Long estateId;
    private final Address address;
    private final State state;

    @Builder
    public EstateOwnerDto(Long estateId, Address address, State state) {
        this.estateId = estateId;
        this.address = address;
        this.state = state;
    }
}
