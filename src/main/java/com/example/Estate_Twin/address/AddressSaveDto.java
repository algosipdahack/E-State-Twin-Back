package com.example.Estate_Twin.address;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressSaveDto {
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
    public Address toEntity() {
        return Address.builder()
                .block(block)
                .borough(borough)
                .city(city)
                .roadName(roadName)
                .buildingName(buildingName)
                .complexName(complexName)
                .mainBuildingNumber(mainBuildingNumber)
                .subBuildingNumber(subBuildingNumber)
                .town(town)
                .unit(unit)
                .build();
    }
}
