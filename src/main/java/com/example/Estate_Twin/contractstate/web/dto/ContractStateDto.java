package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;

public class ContractStateDto {
    private final Long id;
    private final State state;
    private final EstateResponseDto estate;

    public ContractStateDto(ContractState contractState) {
        this.id = contractState.getId();
        this.state = contractState.getState();
        this.estate = new EstateResponseDto(contractState.getEstate());
    }
}
