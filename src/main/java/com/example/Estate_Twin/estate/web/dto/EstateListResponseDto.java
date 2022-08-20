package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;

import lombok.Getter;

@Getter
public class EstateListResponseDto {
    private final Long  id;
    private final House house;
    private final ContractState contractState;
    private final TransactionType transactionType;
    private final String estateThumbNail;
    private final Rank rank;
    private final String address;


    public EstateListResponseDto(Estate estate) {
        this.id = estate.getId();
        this.house = estate.getHouse();
        this.contractState = estate.getContractState();
        this.transactionType = estate.getTransactionType();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.rank = estate.getRank();
        this.address = estate.getAddress();
    }
}
