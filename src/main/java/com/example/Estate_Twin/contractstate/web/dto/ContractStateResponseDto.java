package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import lombok.Getter;

@Getter
public class ContractStateResponseDto {
    private final State state;
    private final Estate estate;

    public ContractStateResponseDto(ContractState contractState) {
        this.state = contractState.getState();
        this.estate = contractState.getEstate();
    }
}
