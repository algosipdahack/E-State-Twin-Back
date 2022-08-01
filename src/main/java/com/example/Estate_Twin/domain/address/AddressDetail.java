package com.example.Estate_Twin.domain.address;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "addressdetail")
public class AddressDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressdetail_id")
    private long id;

    @Column
    private String addressId;

    @Column
    private String town;

    @Column
    private String complexName;

    @Column
    private String block;

    @Column
    private String unit;

    @Column
    private String roadName;

    @Column
    private int mainBuildingNumber;

    @Column
    private int subBuildingNumber;

    @Column
    private String buildingName;

    @Builder // 빌더 형태로 만들어줌
    public AddressDetail(String addressId,String town,String complexName,String block,String unit,String roadName,
                         int mainBuildingNumber,int subBuildingNumber,String buildingName
    ) {//생성자
        this.addressId = addressId;
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
