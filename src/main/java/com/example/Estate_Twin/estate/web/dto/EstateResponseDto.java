package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.Estate;
import lombok.Getter;


@Getter
public class EstateResponseDto {
    private final Long id;
    private final String state;
    private final String transactionType;
    private final String estateThumbNail;
    private final String content;
    private final String city;
    private final String ad_distinct;
    private final String address;

    public EstateResponseDto(Estate estate) {
        this.id = estate.getId();
        this.state = estate.getState().toString();
        this.transactionType = estate.getTransactionType().toString();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.content = estate.getContent();
        this.city = estate.getCity();
        this.ad_distinct = estate.getAd_distinct();
        this.address = estate.getAddress();
    }
}
