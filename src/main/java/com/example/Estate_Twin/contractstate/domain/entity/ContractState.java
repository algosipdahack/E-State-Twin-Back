package com.example.Estate_Twin.contractstate.domain.entity;

import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "contractstate")
public class ContractState extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractstate_id")
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private State state = State.BROKER_BEFORE;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @Builder // 빌더 형태로 만들어줌
    public ContractState(State state, Estate estate) {
        this.estate = estate;
        this.state = state;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }
}
