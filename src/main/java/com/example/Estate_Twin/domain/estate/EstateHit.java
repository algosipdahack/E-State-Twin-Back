package com.example.Estate_Twin.domain.estate;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "estatehit")
public class EstateHit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estatehit_id")
    private Date date;

    @OneToOne(mappedBy = "estateHit")
    private Estate estate;

    @Column
    private long totalHit;

    @Column
    private long weeklyHit;
}
