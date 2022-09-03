package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.house.web.dto.HouseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EstateDto {
    private final Long id;
    private final String state;
    private final String transactionType;
    private final String estateThumbNail;
    private final String content;
    private final String city;
    private final String borough;
    private final String town;
    private final AddressDto address;
    private final HouseDto house;
    private final String model;
    private final LocalDateTime createdAt;

    public EstateDto(Estate estate) {
        this.id = estate.getId();
        this.state = estate.getState().toString();
        this.transactionType = estate.getTransactionType().toString();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.content = estate.getContent();
        this.city = estate.getCity();
        this.borough = estate.getBorough();
        this.town = estate.getTown();
        this.model = estate.getModel();
        this.createdAt = estate.getCreatedDate();
        this.address = new AddressDto(estate.getAddress());
        this.house = new HouseDto(estate.getHouse());
    }
}
