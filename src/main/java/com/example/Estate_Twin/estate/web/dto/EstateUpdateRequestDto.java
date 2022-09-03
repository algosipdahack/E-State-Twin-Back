package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressUpdateRequestDto;
import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import lombok.*;

@Getter
@NoArgsConstructor
public class EstateUpdateRequestDto {
    private TransactionType transactionType;
    private String estateThumbNail;
    private String content;
    private String thumbNail3D;
    private String model;
    private String arCam;
    private String city;
    private String borough;
    private String town;
    private AddressUpdateRequestDto address;

    @Builder
    public EstateUpdateRequestDto(String transactionType,
                                  String estateThumbNail, String content, String model,
                                  String arCam, String thumbNail3D, AddressUpdateRequestDto address) {
        this.transactionType = TransactionType.of(transactionType);
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.model = model;
        this.arCam = arCam;
        this.thumbNail3D = thumbNail3D;
        this.address = address;
        this.city = address.getCity();
        this.borough = address.getBorough();
        this.town = address.getTown();
    }
}
