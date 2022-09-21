package com.example.Estate_Twin.address.web.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class AddressUpdateRequestDto {
    private String city;
    private String borough;
    private String town;
    private String complexName;
    private String block;
    private String unit;
    private String roadName;
    private int mainBuildingNumber;
    private int subBuildingNumber;
    private String buildingName;
}
