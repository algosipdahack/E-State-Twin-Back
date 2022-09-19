package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class EstateMainDto {
    private final Long id;
    private final String estateThumbNail;
    private final String town;
    private final String thumbnail3D;
    private final TransactionType transactionType;
    private final Long sellingFee;
    private final EstateType estateType;

    @QueryProjection
    public EstateMainDto(Long id, String estateThumbNail, String town, String thumbnail3D, TransactionType transactionType, Long sellingFee, EstateType estateType) {
        this.id = id;
        this.estateThumbNail = estateThumbNail;
        this.thumbnail3D = thumbnail3D;
        this.transactionType = transactionType;
        this.town = town;
        this.sellingFee = sellingFee;
        this.estateType = estateType;
    }
}
