package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.constractstate.domain.ConstractState;
import com.example.Estate_Twin.estate.domain.Estate;
import com.example.Estate_Twin.estate.domain.EstateHit;
import com.example.Estate_Twin.estate.domain.Rank;
import com.example.Estate_Twin.estate.domain.TransactionType;
import com.example.Estate_Twin.house.domain.House;
import com.example.Estate_Twin.media.domain.Media;
import com.example.Estate_Twin.user.domain.Broker;
import lombok.Getter;

import java.util.List;

@Getter
public class EstateResponseDto {
    private final Long id;
    private final House house;
    private final Broker broker;
    private final List<Media> estateMedia;
    private final ConstractState constractState;
    private final EstateHit estateHit;
    private final TransactionType transactionType;
    private final String estateThumbNail;
    private final String content;
    private final Rank rank;
    private final String model;
    private final String arCam;
    private final String city;
    private final String ad_distinct;
    private final String address;


    public EstateResponseDto(Estate estate) {
        this.id = estate.getEstateId();
        this.house = estate.getHouse();
        this.broker = estate.getBroker();
        this.estateMedia = estate.getEstateMedia();
        this.constractState = estate.getConstractState();
        this.estateHit = estate.getEstateHit();
        this.transactionType = estate.getTransactionType();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.content = estate.getContent();
        this.rank = estate.getRank();
        this.model = estate.getModel();
        this.arCam = estate.getArCam();
        this.city = estate.getCity();
        this.ad_distinct = estate.getAd_distinct();
        this.address = estate.getAddress();
    }
}
