package com.example.Estate_Twin.domain.constractstate;

import com.example.Estate_Twin.domain.checklist.CheckList;
import com.example.Estate_Twin.domain.estate.Estate;
import lombok.Data;
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
    private int state;

    @Column
    private Date date;

    @OneToOne(mappedBy = "constractState")
    private Estate estate;
}
