package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ContractStateUpdateRequestDto {
    private State state;

    @Builder
    public ContractStateUpdateRequestDto(State state) {
        this.state = state;
    }

    public ContractState toEntity() {
        return ContractState.builder()
                .state(state)
                .build();
    }
}
