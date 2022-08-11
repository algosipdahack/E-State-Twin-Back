package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.constractstate.domain.ConstractState;
import com.example.Estate_Twin.estate.domain.Estate;
import com.example.Estate_Twin.estate.domain.EstateHit;
import com.example.Estate_Twin.estate.domain.Rank;
import com.example.Estate_Twin.media.domain.Media;
import lombok.Getter;

import java.util.List;

@Getter
public class EstateResponseDto {
    private final Long id;
    private final List<Media> estateMedia;
    private final String state;
    private final Long hit;
    private final String transactionType;
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
        this.estateMedia = estate.getEstateMedia();
        this.state = estate.getConstractState().toString();
        this.hit = estate.getEstateHit().getTotalHit();
        this.transactionType = estate.getTransactionType().toString();
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
