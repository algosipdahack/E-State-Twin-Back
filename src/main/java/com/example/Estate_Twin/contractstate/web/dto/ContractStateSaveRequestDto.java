package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ContractStateSaveRequestDto {
    private State state;
    private LocalDateTime date;

    @Builder
    public ContractStateSaveRequestDto(State state, LocalDateTime date, Estate estate) {
        this.state = state;
        this.date = date;
    }

    public ContractState toEntity() {
        return ContractState.builder()
                .date(date)
                .state(state)
                .build();
    }
}
