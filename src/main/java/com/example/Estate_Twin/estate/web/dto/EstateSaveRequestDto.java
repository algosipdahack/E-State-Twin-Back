package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.constractstate.domain.ConstractState;
import com.example.Estate_Twin.estate.domain.Estate;
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

import java.util.List;

@Getter
@NoArgsConstructor
public class EstateSaveRequestDto {
    private House house;
    private Broker broker;
    private User owner;
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
    public EstateSaveRequestDto(House house, Broker broker, User owner, List<Media> estateMedia, ConstractState constractState, EstateHit estateHit, TransactionType transactionType, String estateThumbNail, String content, Rank rank, String model, String arCam, String ad_distinct, String address) {
        this.house = house;
        this.broker = broker;
        this.owner = owner;
        this.estateMedia = estateMedia;
        this.constractState = constractState;
        this.estateHit = estateHit;
        this.transactionType = transactionType;
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.rank = rank;
        this.model = model;
        this.arCam = arCam;
        this.ad_distinct = ad_distinct;
        this.address = address;
    }

    public Estate toEntity() {
        return Estate.builder()
                .content(content)
                .estateHit(estateHit)
                .estateMedia(estateMedia)
                .estateThumbNail(estateThumbNail)
                .broker(broker)
                .transactionType(transactionType)
                .owner(owner)
                .ad_distinct(ad_distinct)
                .address(address)
                .city(city)
                .rank(rank)
                .model(model)
                .house(house)
                .arCam(arCam)
                .build();
    }

}
