package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.EstateDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ContractStateResponseDto {
    private final Long id;
    private final State state;
    private final EstateDto estate;
    private final LocalDateTime date;

    public ContractStateResponseDto(ContractState contractState) {
        this.id = contractState.getId();
        this.state = contractState.getState();
        this.date = contractState.getCreatedDate();
        this.estate = new EstateDto(contractState.getEstate());
    }
}
