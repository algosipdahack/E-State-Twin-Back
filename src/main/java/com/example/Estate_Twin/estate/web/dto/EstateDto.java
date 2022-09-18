package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.house.web.dto.HouseDto;
import com.example.Estate_Twin.media.web.dto.MediaResponseDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class EstateDto {
    private final String state;
    private final String transactionType;
    private final String estateThumbNail;
    private final String content;
    private final String city;
    private final String borough;
    private final String town;
    private final String model;
    private final LocalDateTime createdAt;
    private final List<MediaResponseDto> media;
    private final List<AssetResponseDto> assets;
    private final AddressDto address;
    private final HouseDto house;
    private final EstateHitDto estatehit;
    @QueryProjection
    public EstateDto(Estate estate) {
        this.state = estate.getState().toString();
        this.transactionType = estate.getTransactionType().toString();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.content = estate.getContent();
        this.city = estate.getCity();
        this.borough = estate.getBorough();
        this.town = estate.getTown();
        this.model = estate.getModel();
        this.createdAt = estate.getCreatedDate();
        this.address = null;
        this.estatehit = null;
        this.house = null;
        this.media = new ArrayList<>();
        estate.getEstateMedia().forEach(eMedia -> this.media.add(new MediaResponseDto(eMedia)));
        this.assets = null;
    }
}
