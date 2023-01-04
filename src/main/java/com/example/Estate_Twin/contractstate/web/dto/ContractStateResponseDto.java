package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ContractStateResponseDto {
    private final Long id;
    @Schema(description = "거래 상태", example = "BROKER_BEFORE,POST_DOING,POST_DONE, CONTRACT_REQUEST, CONFIRM_BROKER, CONFIRM_OWNER, CHECKLIST_DOING, CONTRACT_DONE")
    private final String state;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime date;
    private final Long estateId;

    @QueryProjection
    @Builder
    public ContractStateResponseDto(ContractState contractState) {
        this.id = contractState.getId();
        this.state = contractState.getState().toString();
        this.date = contractState.getCreatedDate();
        this.estateId = contractState.getEstate().getId();
    }
}
