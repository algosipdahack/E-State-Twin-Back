package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.EstateDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ContractStateResponseDto {
    private final Long id;
    private final State state;
    private final LocalDateTime date;

    @QueryProjection
    public ContractStateResponseDto(ContractState contractState) {
        this.id = contractState.getId();
        this.state = contractState.getState();
        this.date = contractState.getCreatedDate();
    }
}
