package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.house.web.dto.HouseDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;


@Data
public class EstateResponseDto {
    private final Long id;
    private final String state;
    private final String transactionType;
    private final String estateThumbNail;
    private final String content;
    private final String model;
    private final LocalDateTime createdAt;
    private boolean isPosted;
    private boolean ownerConfirmYN;
    private boolean brokerConfirmYN;
    private final List<String> estatePhotos;
    private List<AssetResponseDto> assets;
    private AddressDto address;
    private HouseDto house;
    private EstateHitDto estatehit;

    @QueryProjection
    public EstateResponseDto(Estate estate) {
        this.id = estate.getId();
        this.state = estate.getState().toString();
        this.transactionType = estate.getTransactionType().toString();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.content = estate.getContent();
        this.model = estate.getModel();
        this.createdAt = estate.getCreatedDate();
        this.isPosted = estate.isPosted();
        this.ownerConfirmYN = estate.isOwnerConfirmYN();
        this.brokerConfirmYN = estate.isBrokerConfirmYN();

        this.address = null;
        this.estatehit = null;
        this.house = null;

        this.estatePhotos = new ArrayList<>();
        estate.getEstateMedia().forEach(eMedia -> this.estatePhotos.add(eMedia));

        this.assets = null;
    }
    public void setNull(EstateHitDto estatehit, List<AssetResponseDto> assets, AddressDto address, HouseDto house) {
        this.estatehit = estatehit;
        this.assets = new ArrayList<>();
        assets.forEach(asset -> this.assets.add(asset));
        this.address = address;
        this.house = house;
    }
}