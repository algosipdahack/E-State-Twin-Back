package com.example.Estate_Twin.address;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {
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

    @Builder // 빌더 형태로 만들어줌
    public Address(String city, String borough, String town, String complexName, String block, String unit, String roadName,
                   int mainBuildingNumber, int subBuildingNumber, String buildingName)
    {
        this.city = city;
        this.borough = borough;
        this.roadName = roadName;
        this.mainBuildingNumber = mainBuildingNumber;
        this.subBuildingNumber = subBuildingNumber;
        this.buildingName = buildingName;
        this.town = town;
        this.complexName = complexName;
        this.block = block;
        this.unit = unit;
    }
}
