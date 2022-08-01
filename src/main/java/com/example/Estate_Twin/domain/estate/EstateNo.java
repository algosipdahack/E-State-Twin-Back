package com.example.Estate_Twin.domain.estate;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Embeddable
@NoArgsConstructor
public class EstateNo {
    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "estateno_id")
    private Date date;

    @Column
    private int sequence;
}
