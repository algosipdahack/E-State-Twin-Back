package com.example.Estate_Twin.address.web.dto;

import com.example.Estate_Twin.address.data.entity.Address;
import lombok.Getter;

@Getter
public class AddressDto {
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

    public AddressDto(Address address) {
        this.city = address.getCity();
        this.borough = address.getBorough();
        this.town = address.getTown();
        this.complexName = address.getComplexName();
        this.block = address.getBlock();
        this.unit = address.getUnit();
        this.roadName = address.getRoadName();
        this.mainBuildingNumber = address.getMainBuildingNumber();
        this.subBuildingNumber = address.getSubBuildingNumber();
        this.buildingName = address.getBuildingName();
    }
}
