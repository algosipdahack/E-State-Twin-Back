package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateUpdateRequestDto;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class EstateSaveRequestDto {
    private TransactionType transactionType;
    private String estateThumbNail;
    private String content;
    private String city;
    private String borough;
    private String town;
    private String model;
    private AddressSaveRequestDto address;
    private HouseSaveRequestDto house;
    private List<AssetSaveRequestDto> assetSaveRequestDtos;
    private ContractStateUpdateRequestDto contractState;
    @Builder
    public EstateSaveRequestDto(String transactionType, String model,
                                String estateThumbNail, String content, HouseSaveRequestDto house,
                                AddressSaveRequestDto address, List<AssetSaveRequestDto> assets,
                                ContractStateUpdateRequestDto contractState) {
        this.transactionType = TransactionType.of(transactionType);
        this.model = model;
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.address = address;
        this.contractState = contractState;
        this.house = house;
        this.assetSaveRequestDtos = new ArrayList<>();
        assets.forEach(asset -> {
            assetSaveRequestDtos.add(asset);
        });
        this.city = address.getCity();
        this.borough = address.getBorough();
        this.town = address.getTown();
    }

    public Estate toEntity() {
        return Estate.builder()
                .content(content)
                .estateThumbNail(estateThumbNail)
                .model(model)
                .transactionType(transactionType)
                .town(town)
                .city(city)
                .borough(borough)
                .build();
    }

}
