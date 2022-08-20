package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EstateUpdateRequestDto {

    private TransactionType transactionType;
    private String estateThumbNail;
    private String content;
    private String thumbNail3D;
    private ContractState contractState;
    private String model;

    private String arCam;

    private String city;

    private String borough;

    @Builder
    public EstateUpdateRequestDto(String transactionType, ContractState contractState,
                                  String estateThumbNail, String content, String model,
                                  String arCam, String city, String borough, String thumbNail3D) {
        this.transactionType = TransactionType.of(transactionType);
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.model = model;
        this.arCam = arCam;
        this.contractState = contractState;
        this.city = city;
        this.borough = borough;
        this.thumbNail3D = thumbNail3D;
    }
}
