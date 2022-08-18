package com.example.Estate_Twin.estate.domain.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Embeddable
@NoArgsConstructor
public class EstateNo {
    @Id
    @Column(name = "estateno_id")
    private LocalDateTime date;

    @Column
    private int sequence;
}
