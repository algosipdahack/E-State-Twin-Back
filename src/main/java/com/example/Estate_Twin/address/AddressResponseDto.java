package com.example.Estate_Twin.address;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class AddressResponseDto {
    private final String city;
    private final String borough;
    private final String town;
    private final String complexName;
    private final String block;
    private final String unit;
    private final String roadName;
    private final int mainBuildingNumber;
    private final int subBuildingNumber;
    private final String buildingName;
    @QueryProjection
    public AddressResponseDto(Address address) {
        this.city = address.getCity();
        this.borough = address.getBorough();
        this.block = address.getBlock();
        this.town = address.getTown();
        this.unit = address.getUnit();
        this.roadName = address.getRoadName();
        this.buildingName = address.getBuildingName();
        this.mainBuildingNumber = address.getMainBuildingNumber();
        this.subBuildingNumber = address.getSubBuildingNumber();
        this.complexName = address.getComplexName();
    }
}
