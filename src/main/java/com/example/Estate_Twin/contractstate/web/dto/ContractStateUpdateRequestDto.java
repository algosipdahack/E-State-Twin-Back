package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ContractStateUpdateRequestDto {
    @Schema(description = "거래 상태", example = "BEFORE, RESERVATION, DOING, DONE")
    private String state;
    public ContractState toEntity() {
        return ContractState.builder()
                .state(State.of(state))
                .build();
    }
}
