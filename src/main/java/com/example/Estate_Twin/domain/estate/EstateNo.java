package com.example.Estate_Twin.domain.estate;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class EstateNo {
    @Column(name = "estate_number")
    private String number;

}
