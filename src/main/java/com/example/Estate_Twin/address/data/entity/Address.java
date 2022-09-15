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

    @Column
    private String city;

    @Column
    private String borough;

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


    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private User user;

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
    public void setEstate(Estate estate) {
        this.estate = estate;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setId(Long id) {this.id = id;}
}
