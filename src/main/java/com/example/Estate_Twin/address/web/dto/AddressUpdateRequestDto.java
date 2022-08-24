package com.example.Estate_Twin.address.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public AddressUpdateRequestDto(String city, String borough, String town, String complexName, String block,
                                 String unit, String roadName, int mainBuildingNumber, int subBuildingNumber,
                                 String buildingName) {
        this.city = city;
        this.borough = borough;
        this.town = town;
        this.complexName = complexName;
        this.block = block;
        this.unit = unit;
        this.roadName = roadName;
        this.mainBuildingNumber = mainBuildingNumber;
        this.subBuildingNumber = subBuildingNumber;
        this.buildingName = buildingName;
    }
}
