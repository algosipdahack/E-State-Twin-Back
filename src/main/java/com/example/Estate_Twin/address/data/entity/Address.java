package com.example.Estate_Twin.address.data.entity;

import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
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
                   int mainBuildingNumber, int subBuildingNumber, String buildingName
    ) {//생성자
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
    public void setId(Long id) { this.id = id; }
}
