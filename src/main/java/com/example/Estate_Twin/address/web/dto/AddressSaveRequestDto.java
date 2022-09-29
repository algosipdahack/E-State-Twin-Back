package com.example.Estate_Twin.address.web.dto;

import com.example.Estate_Twin.address.data.entity.Address;
import lombok.*;

@Getter
@NoArgsConstructor
public class AddressSaveRequestDto {
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
    public AddressSaveRequestDto(String city, String borough, String town, String complexName, String block,
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

    public Address toEntity() {
        return Address.builder()
                .buildingName(buildingName)
                .subBuildingNumber(subBuildingNumber)
                .unit(unit)
                .mainBuildingNumber(mainBuildingNumber)
                .roadName(roadName)
                .block(block)
                .complexName(complexName)
                .town(town)
                .borough(borough)
                .city(city)
                .build();
    }
}
