package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.house.web.dto.HouseDto;
import com.example.Estate_Twin.media.web.dto.MediaDto;
import com.example.Estate_Twin.media.web.dto.MediaResponseDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class EstateResponseDto {
    private final String state;
    private final String transactionType;
    private final String estateThumbNail;
    private final String content;
    private final String city;
    private final String borough;
    private final String town;
    private final String model;
    private final List<MediaResponseDto> media;
    private final AddressDto address;
    private final HouseDto house;

    public EstateResponseDto(Estate estate) {
        this.state = estate.getState().toString();
        this.transactionType = estate.getTransactionType().toString();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.content = estate.getContent();
        this.city = estate.getCity();
        this.borough = estate.getBorough();
        this.town = estate.getTown();
        this.model = estate.getModel();
        this.address = new AddressDto(estate.getAddress());
        this.house = new HouseDto(estate.getHouse());
        this.media = new ArrayList<>();
        estate.getEstateMedia().forEach(eMedia -> media.add(new MediaResponseDto(eMedia)));
    }

}
