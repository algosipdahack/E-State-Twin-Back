package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.estate.domain.entity.*;

import com.example.Estate_Twin.house.web.dto.HouseDto;
import lombok.Getter;

@Getter
public class EstateListResponseDto {
    private final Long  id;
    private final HouseDto house;
    private final ContractState contractState;
    private final TransactionType transactionType;
    private final String estateThumbNail;
    private final Rank rank;
    private final AddressDto address;


    public EstateListResponseDto(Estate estate) {
        this.id = estate.getId();
        this.house = new HouseDto(estate.getHouse());
        this.contractState = estate.getContractState();
        this.transactionType = estate.getTransactionType();
        this.estateThumbNail = estate.getEstateThumbNail();
        this.rank = estate.getRank();
        this.address = new AddressDto(estate.getAddress());
    }
}
