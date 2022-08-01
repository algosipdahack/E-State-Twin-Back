package com.example.Estate_Twin.domain.address;

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
    private String ComplexName;

    @Column
    private String Block;

    @Column
    private String Unit;

    @Column
    private String roadName;

    @Column
    private int MainBuildingNumber;

    @Column
    private int SubBuildingNumber;

    @Column
    private String BuildingName;



}
