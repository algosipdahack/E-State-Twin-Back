package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.EstateDto;
import lombok.Getter;

@Getter
public class ContractStateResponseDto {
    private final State state;
    private final EstateDto estate;

    public ContractStateResponseDto(ContractState contractState) {
        this.state = contractState.getState();
        this.estate = new EstateDto(contractState.getEstate());
    }
}
