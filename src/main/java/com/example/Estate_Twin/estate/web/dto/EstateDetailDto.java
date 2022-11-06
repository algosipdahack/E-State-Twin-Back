package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.house.web.dto.HouseResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Data
public class EstateDetailDto {
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
    @Schema(description = "문의한 매물인지 아닌지")
    private boolean isInquiry;
    private HouseResponseDto house;
    private EstateHitDto estatehit;
    private List<AssetResponseDto> assets;

    public EstateDetailDto(Estate estate) {
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
        this.house = new HouseResponseDto(estate.getHouse());
        this.estatehit = new EstateHitDto(estate.getEstateHit());
        this.estatePhotos = new ArrayList<>();
        estate.getEstatePhoto().forEach(eMedia -> this.estatePhotos.add(eMedia));

        this.assets = new ArrayList<>();
        estate.getAssets().forEach(asset -> this.assets.add(new AssetResponseDto(asset)));
    }

    public void setIsInquiry(boolean isInquiry) {
        this.isInquiry = isInquiry;
    }
}