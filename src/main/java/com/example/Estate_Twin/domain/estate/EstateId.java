package com.example.Estate_Twin.domain.estate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class EstateId implements Serializable {
    private String estateId;
    private String houseId;
    private String brokerId;
    private String ownerId;
}
