package com.example.Estate_Twin.domain.asset;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class AssetId implements Serializable {
    private String houseId;
    private String assetId;
}
