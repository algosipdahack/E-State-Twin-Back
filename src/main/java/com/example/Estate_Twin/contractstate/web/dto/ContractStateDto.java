package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ContractStateDto {
    private final State state;
    private final LocalDateTime date;
    private final Long estateId;
    @QueryProjection
    public ContractStateDto(ContractState contractState) {
        this.state = contractState.getState();
        this.date = contractState.getCreatedDate();
        this.estateId = contractState.getEstate().getId();
    }
}
