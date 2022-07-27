package com.example.Estate_Twin.domain.estate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EstateNo implements Serializable { // JPA에서 식별자 타입은 Serializable타입이어야 함
    @Column(name = "estate_number")
    private String number;
}
