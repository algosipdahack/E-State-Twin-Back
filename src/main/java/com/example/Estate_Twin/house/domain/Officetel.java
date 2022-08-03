package com.example.Estate_Twin.house.domain;

import com.example.Estate_Twin.house.domain.House;

import javax.persistence.*;

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

}
