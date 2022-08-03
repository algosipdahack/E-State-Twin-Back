package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.constractstate.domain.ConstractState;
import com.example.Estate_Twin.estate.domain.Estate;
import com.example.Estate_Twin.estate.domain.Rank;
import com.example.Estate_Twin.estate.domain.TransactionType;
import com.example.Estate_Twin.house.domain.House;

import lombok.Getter;

@Getter
public class EstateListResponseDto {
    private final Long id;
    private final House house;
    private final ConstractState constractState;
    private final TransactionType transactionType;
    private final String estateThumbNail;
    private final Rank rank;
    private final String address;


    public EstateListResponseDto(Estate estate) {
        this.id = estate.getEstateId();
        this.house = estate.getHouse();
        this.constractState = estate.getConstractState();
        this.transactionType = estate.getTransactionType();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.rank = estate.getRank();
        this.address = estate.getAddress();
    }
}
