package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class EstateMainDto {
    private final Long id;
    private final String estateThumbNail;
    private final String town;
    private final String thumbnail3D;
    private final Long sellingFee;
    @Schema(description = "매물 거래 유형", example = "MONTHLYRENT, LEASE, TRADING")
    private final String transactionType;
    @Schema(description = "매물 거래 종류", example = "APARTMENT, OFFICETELS")
    private final String estateType;

    @QueryProjection
    public EstateMainDto(Long id, String estateThumbNail, String town, String thumbnail3D, TransactionType transactionType, Long sellingFee, EstateType estateType) {
        this.id = id;
        this.estateThumbNail = estateThumbNail;
        this.thumbnail3D = thumbnail3D;
        this.transactionType = transactionType.toString();
        this.town = town;
        this.sellingFee = sellingFee;
        this.estateType = estateType.toString();
    }
}
