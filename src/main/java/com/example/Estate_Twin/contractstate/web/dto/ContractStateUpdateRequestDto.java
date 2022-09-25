package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ContractStateUpdateRequestDto {
    @Schema(description = "거래 상태", example = "BROKER_BEFORE,POST_DOING,POST_DONE, CONTRACT_REQUEST, CONFIRM_BROKER, CONFIRM_OWNER, CHECKLIST_DOING, CONTRACT_DONE")
    private String state;
    public ContractState toEntity() {
        return ContractState.builder()
                .state(State.of(state))
                .build();
    }
}
