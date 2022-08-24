package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import lombok.Getter;

@Getter
public class ContractStateResponseDto {
    private final State state;
    private final EstateResponseDto estate;

    public ContractStateResponseDto(ContractState contractState) {
        this.state = contractState.getState();
        this.estate = new EstateResponseDto(contractState.getEstate());
    }
}
