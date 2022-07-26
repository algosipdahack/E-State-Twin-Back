package com.example.Estate_Twin.domain.constractstate;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "constractstate")
public class ConstractState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constractstate_id")
    private long id;
}
