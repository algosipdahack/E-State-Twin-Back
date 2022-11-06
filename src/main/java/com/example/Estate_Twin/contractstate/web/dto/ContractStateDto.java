package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ContractStateDto {
    private final State state;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime date;
    private final Long estateId;
    @QueryProjection
    public ContractStateDto(ContractState contractState) {
        this.state = contractState.getState();
        this.date = contractState.getCreatedDate();
        this.estateId = contractState.getEstate().getId();
    }
}
