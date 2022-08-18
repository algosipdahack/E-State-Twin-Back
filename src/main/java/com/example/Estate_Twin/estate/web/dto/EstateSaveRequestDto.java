package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.house.domain.entity.House;
import lombok.*;

@Getter
@NoArgsConstructor
public class EstateSaveRequestDto {
    private TransactionType transactionType;
    private String estateThumbNail;
    private String content;
    private String city;
    private String borough;
    private String address;

    private House house;

    @Builder
    public EstateSaveRequestDto(String transactionType,
                                String estateThumbNail, String content,
                                String borough, String address, String city) {
        this.transactionType = TransactionType.of(transactionType);
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.borough = borough;
        this.address = address;
        this.city = city;
    }

    public Estate toEntity() {
        return Estate.builder()
                .content(content)
                .estateThumbNail(estateThumbNail)
                .transactionType(transactionType)
                .borough(borough)
                .address(address)
                .city(city)
                .house(house)
                .build();
    }

    public void setHouse(House house) {
        this.house = house;
    }

}
