package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.Estate;

public class EstateDto {
    private final Long id;
    private final String state;
    private final String transactionType;
    private final String estateThumbNail;
    private final String content;
    private final String city;
    private final String borough;
    private final String address;

    public EstateDto(Estate estate) {
        this.id = estate.getId();
        this.state = estate.getState().toString();
        this.transactionType = estate.getTransactionType().toString();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.content = estate.getContent();
        this.city = estate.getCity();
        this.borough = estate.getBorough();
        this.address = estate.getAddress();
    }
}
