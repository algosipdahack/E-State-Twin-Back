package com.example.Estate_Twin.house.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "officetel")
public class Officetel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "officetel_id")
    private Long id;
    private Boolean elevator;
    private Boolean duplex;
    private Boolean loft;
    private Boolean builtIn;
    private Boolean veranda;
    @OneToOne
    @JoinColumn(name="house_id")
    private House house;

    @Builder
    public Officetel(Boolean elevator, Boolean duplex, Boolean loft, Boolean builtIn, Boolean veranda) {
        this.elevator = elevator;
        this.duplex = duplex;
        this.loft = loft;
        this.builtIn = builtIn;
        this.veranda = veranda;
    }

    public void setHouse(House house) {
        this.house = house;
        house.setOfficetel();
    }
}
