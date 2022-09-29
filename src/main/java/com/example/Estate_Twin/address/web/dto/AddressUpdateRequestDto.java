package com.example.Estate_Twin.address.web.dto;

import com.example.Estate_Twin.address.data.entity.Address;
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
