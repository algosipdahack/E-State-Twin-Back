package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.constractstate.domain.ConstractState;
import com.example.Estate_Twin.estate.domain.EstateHit;
import com.example.Estate_Twin.estate.domain.Rank;
import com.example.Estate_Twin.estate.domain.TransactionType;
import com.example.Estate_Twin.house.domain.House;
import com.example.Estate_Twin.media.domain.Media;
import com.example.Estate_Twin.user.domain.Broker;
import com.example.Estate_Twin.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class EstateUpdateRequestDto {

    private Broker broker;

    private List<Media> estateMedia;

    private ConstractState constractState;

    private EstateHit estateHit;

    private TransactionType transactionType;

    private String estateThumbNail;

    private String content;

    private Rank rank;

    private String model;

    private String arCam;

    private String city;

    private String ad_distinct;

    private String address;
    @Builder
    public EstateUpdateRequestDto(Broker broker, List<Media> estateMedia, ConstractState constractState, EstateHit estateHit, TransactionType transactionType, String estateThumbNail, String content, Rank rank, String model, String arCam, String city, String ad_distinct, String address) {
        this.broker = broker;
        this.estateMedia = estateMedia;
        this.constractState = constractState;
        this.estateHit = estateHit;
        this.transactionType = transactionType;
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.rank = rank;
        this.model = model;
        this.arCam = arCam;
        this.city = city;
        this.ad_distinct = ad_distinct;
        this.address = address;
    }
}
