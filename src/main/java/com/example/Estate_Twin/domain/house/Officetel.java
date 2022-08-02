package com.example.Estate_Twin.domain.house;

import com.example.Estate_Twin.domain.estate.Estate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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

}
