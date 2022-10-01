package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
public class EstateUpdateRequestDto {
    private String transactionType;
    private String estateThumbNail;
    private String content;
    private String model;
    private List<String> estatePhotos;
    private Address address;
    private HouseUpdateRequestDto house;
}
