package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.web.dto.EstateDto;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ContractStateDto {
    private final Long id;
    private final State state;
    private final LocalDateTime date;
    private final EstateDto estate;

    public ContractStateDto(ContractState contractState) {
        this.id = contractState.getId();
        this.state = contractState.getState();
        this.date = contractState.getCreatedDate();
        this.estate = new EstateDto(contractState.getEstate());
    }
}
