package com.example.Estate_Twin.constractstate.domain;

import com.example.Estate_Twin.estate.domain.Estate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "constractstate")
public class ConstractState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constractstate_id")
    private long id;

    @Column
    private State state;

    @Column
    private Date date;

    @OneToOne
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @Builder // 빌더 형태로 만들어줌
    public ConstractState(State state, Date date, Estate estate) {
        this.date = date;
        this.estate = estate;
        this.state = state;
    }
}
