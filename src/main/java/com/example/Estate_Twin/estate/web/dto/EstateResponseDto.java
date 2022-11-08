package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.asset.web.dto.AssetSummaryDto;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.EstateHit;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.web.dto.HouseResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class EstateResponseDto {
    private final Long id;
    private final String state;
    private final String transactionType;
    private final String estateThumbNail;
    private final String model;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;
    private boolean isPosted;
    private boolean ownerConfirmYN;
    private boolean brokerConfirmYN;
    private final List<String> estatePhotos;
    private Address address;
    private HouseResponseDto house;
    private EstateHitDto estatehit;
    private List<AssetSummaryDto> assets;

    @QueryProjection
    public EstateResponseDto(Estate estate, House house, EstateHit estateHit, List<Asset> assets) {
        this.id = estate.getId();
        this.state = estate.getState().toString();
        this.transactionType = estate.getTransactionType().toString();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.model = estate.getModel();
        this.createdAt = estate.getCreatedDate();
        this.isPosted = estate.isPosted();
        this.ownerConfirmYN = estate.isOwnerConfirmYN();
        this.brokerConfirmYN = estate.isBrokerConfirmYN();
        this.address = estate.getAddress();

        this.estatePhotos = new ArrayList<>();
        estate.getEstatePhoto().forEach(eMedia -> this.estatePhotos.add(eMedia));
        this.house = new HouseResponseDto(house);
        this.estatehit = new EstateHitDto(estateHit);
        this.assets = new ArrayList<>();
        assets.forEach(asset -> this.assets.add(new AssetSummaryDto(asset)));
    }
}