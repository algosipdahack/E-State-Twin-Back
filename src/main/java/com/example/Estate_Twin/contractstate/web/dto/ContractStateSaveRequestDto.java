package com.example.Estate_Twin.contractstate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ContractStateSaveRequestDto {
    private State state;
    private Estate estate;
    private LocalDateTime date;

    @Builder
    public ContractStateSaveRequestDto(State state, LocalDateTime date) {
        this.state = state;
        this.date = date;
    }

    public ContractState toEntity() {
        return ContractState.builder()
                .date(date)
                .state(state)
                .estate(estate)
                .build();
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }
}
