package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.Estate;

public class ContractStateDto {
    private final Long id;
    private final State state;
    private final Estate estate;

    public ContractStateDto(ContractState contractState) {
        this.id = contractState.getId();
        this.state = contractState.getState();
        this.estate = contractState.getEstate();
    }
}
