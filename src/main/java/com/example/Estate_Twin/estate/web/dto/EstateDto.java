package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.web.dto.HouseDto;
import com.example.Estate_Twin.user.web.dto.*;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.util.*;

@Getter
public class EstateDto {
    private final String estateThumbNail;
    private final String content;
    private final String model;
    private final String thumbnail3D;
    private final String arCam;
    private final boolean isPosted;
    private final boolean ownerConfirmYN;
    private final boolean brokerConfirmYN;
    private final TransactionType transactionType;
    private final State state;
    private final Grade grade;
    private final Address address;
    private final EstateHitDto estateHit;
    private final HouseDto house;
    private final BrokerDto broker;
    private final UserDto owner;
    private final UserDto tanent;
    private final List<String> estateMedia;
    private final List<Asset> assets;
    private final List<DipEstate> dipEstates;
    @QueryProjection
    public EstateDto(Estate estate) {
        this.estateThumbNail = estate.getEstateThumbNail();
        this.content = estate.getContent();
        this.model = estate.getModel();
        this.thumbnail3D = estate.getThumbnail3D();
        this.arCam = estate.getArCam();
        this.isPosted = estate.isPosted();
        this.ownerConfirmYN = estate.isOwnerConfirmYN();
        this.brokerConfirmYN = estate.isBrokerConfirmYN();
        this.transactionType = estate.getTransactionType();
        this.state = estate.getState();
        this.grade = estate.getGrade();
        this.address = estate.getAddress();
        this.estateHit = new EstateHitDto(estate.getEstateHit());
        this.house = new HouseDto(estate.getHouse());
        this.broker = new BrokerDto(estate.getBroker());
        this.owner = new UserDto(estate.getOwner());
        this.tanent = new UserDto(estate.getTanent());

        this.estateMedia = new ArrayList<>();
        estate.getEstateMedia().forEach(media -> this.estateMedia.add(media));
        this.assets = new ArrayList<>();
        estate.getAssets().forEach(asset -> this.assets.add(asset));
        this.dipEstates = new ArrayList<>();
        estate.getDipEstates().forEach(dipEstate -> this.dipEstates.add(dipEstate));
    }
}
