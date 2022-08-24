package com.example.Estate_Twin.contractstate.domain.entity;

import com.example.Estate_Twin.estate.domain.entity.Estate;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "contractstate")
public class ContractState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractstate_id")
    private Long id;

    @Column
    @Enumerated(value = EnumType.ORDINAL)
    private State state = State.BEFORE;
    @Column
    private LocalDateTime date;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @Builder // 빌더 형태로 만들어줌
    public ContractState(State state, LocalDateTime date, Estate estate) {
        this.date = date;
        this.estate = estate;
        this.state = state;
    }


    public void setEstate(Estate estate) {
        this.estate = estate;
    }
}
