package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.ContractState;
import com.example.Estate_Twin.contractstate.domain.State;
import com.example.Estate_Twin.estate.domain.Estate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ContractStateSaveRequestDto {
    private State state;
    private LocalDateTime date;
    private Estate estate;
    @Builder
    public ContractStateSaveRequestDto(State state, LocalDateTime date, Estate estate) {
        this.state = state;
        this.date = date;
        this.estate = estate;
    }

    public ContractState toEntity() {
        return ContractState.builder()
                .date(date)
                .estate(estate)
                .state(state)
                .build();
    }
}
