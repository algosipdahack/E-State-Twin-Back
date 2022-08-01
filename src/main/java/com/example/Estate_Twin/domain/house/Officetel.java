package com.example.Estate_Twin.domain.house;

import com.example.Estate_Twin.domain.estate.Estate;
import lombok.Builder;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("O")
public class Officetel extends House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private long id;

    @Column
    private Boolean elevator;

    @Column
    private Boolean duplex;

    @Column
    private Boolean loft;

    @Column
    private Boolean builtIn;

    @Column
    private Boolean veranda;

    @Builder // 빌더 형태로 만들어줌
    public Officetel(Boolean elevator,Boolean duplex,Boolean loft,Boolean builtIn,Boolean veranda
    ) {//생성자
        this.elevator = elevator;
        this.duplex = duplex;
        this.loft = loft;
        this.builtIn = builtIn;
        this.veranda = veranda;
    }
}
