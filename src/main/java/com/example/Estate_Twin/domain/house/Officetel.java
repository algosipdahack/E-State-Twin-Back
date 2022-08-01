package com.example.Estate_Twin.domain.house;

import javax.persistence.*;

@Entity
@DiscriminatorValue("O")
public class Officetel extends House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private long id;

    @Column
    private Boolean Elevator;

    @Column
    private Boolean Duplex;

    @Column
    private Boolean Loft;

    @Column
    private Boolean BuiltIn;

    @Column
    private Boolean Veranda;
}
