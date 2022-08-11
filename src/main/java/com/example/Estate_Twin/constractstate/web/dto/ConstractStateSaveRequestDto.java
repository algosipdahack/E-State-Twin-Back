package com.example.Estate_Twin.constractstate.web.dto;

import com.example.Estate_Twin.constractstate.domain.ConstractState;
import com.example.Estate_Twin.constractstate.domain.State;
import com.example.Estate_Twin.estate.domain.Estate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class ConstractStateSaveRequestDto {
    private State state;
    private LocalDateTime date;
    private Estate estate;
    @Builder
    public ConstractStateSaveRequestDto(State state, LocalDateTime date, Estate estate) {
        this.state = state;
        this.date = date;
        this.estate = estate;
    }

    public ConstractState toEntity() {
        return ConstractState.builder()
                .date(date)
                .estate(estate)
                .state(state)
                .build();
    }
}
