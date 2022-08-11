package com.example.Estate_Twin.contractstate.domain;

import com.example.Estate_Twin.estate.domain.Estate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "constractstate")
public class ContractState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constractstate_id")
    private Long id;

    @Column
    @Enumerated(value = EnumType.ORDINAL)
    private State state = State.BEFORE;

    @Column
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @Builder // 빌더 형태로 만들어줌
    public ContractState(State state, LocalDateTime date, Estate estate) {
        this.date = date;
        this.estate = estate;
        this.state = state;
    }
    public void updateState(String state) {
        /*this.state.ordinal()
        this.state.ordinal() += 1;*/
    }


}
