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
import com.example.Estate_Twin.user.domain.entity.Broker;
import com.example.Estate_Twin.user.web.dto.BrokerDetailDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class EstateDetailDto {
    private final Long id;
    private final String state;
    private final String transactionType;
    private final String estateThumbNail;
    private final String model;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;
    private final boolean isPosted;
    private final boolean ownerConfirmYN;
    private final boolean brokerConfirmYN;
    private final List<String> estatePhotos;
    private final List<String> estateVideos;
    private final Address address;
    @Setter
    @Schema(description = "문의한 매물인지 아닌지")
    private boolean isInquiry;
    private HouseResponseDto house;
    private BrokerDetailDto broker;
    private EstateHitDto estatehit;
    private List<AssetSummaryDto> assets;

    @QueryProjection
    public EstateDetailDto(Estate estate, House house, Broker broker, EstateHit estateHit, List<Asset> assets) {
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

        this.house = new HouseResponseDto(house);
        this.estatehit = new EstateHitDto(estateHit);

        this.broker = new BrokerDetailDto(broker);

        this.estatePhotos = new ArrayList<>();
        estate.getEstatePhoto().forEach(eMedia -> this.estatePhotos.add(eMedia));

        this.estateVideos = new ArrayList<>();
        estate.getEstateVideo().forEach(video -> this.estateVideos.add(video));

        this.assets = new ArrayList<>();
        assets.forEach(asset -> this.assets.add(new AssetSummaryDto(asset)));
    }
}