package com.example.Estate_Twin.contractstate.domain.entity;

import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import static com.example.Estate_Twin.contractstate.domain.entity.State.CONTRACT_BEFORE;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "contractstate")
public class ContractState extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractstate_id")
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @Builder // 빌더 형태로 만들어줌
    public ContractState(State state, Estate estate) {
        this.estate = estate;
        this.state = state;
    }

    @PrePersist
    public void prePersist() {
        this.state = this.state == null ? CONTRACT_BEFORE : this.state;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }
}
