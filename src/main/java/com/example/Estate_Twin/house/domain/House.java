package com.example.Estate_Twin.house.domain;


import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.estate.domain.Estate;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "house")
public class House extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private long id;

    @Column
    private long deposit;

    @Column
    private long monthlyRent;

    @Column
    private long sellingFee;

    @Column
    private long currentFloors;

    @Column
    private long totalFloors;

    @Column
    private boolean shortTermRent;

    @Column
    private long maintenanceFee;

    @Column
    private String itemsIncludedMaintenanceFee;

    @Column
    private long netRentableArea;

    @Column
    private long rentableArea;

    @Column
    private boolean parking;

    @Column
    private long parkingFee;

    @Column
    private Date moveInAvailableDate;

    @Column
    private long size;

    @Column
    private String heatType;

    @Column
    private String estateType;

    @Column
    private long household;

    @Column
    private Date usageAvailableDate;

    @Column
    private long roomCount;

    @Column
    private long bathCount;

    @OneToMany(
            mappedBy = "house",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true // DB에서 함께 삭제됨
    )
    private List<Asset> assets = new ArrayList<>();

    @OneToOne(mappedBy = "house")
    private Estate estate;


    @Builder // 빌더 형태로 만들어줌
    public House(long deposit, long monthlyRent, long sellingFee, long currentFloors,
                 long totalFloors, boolean shortTermRent, long maintenanceFee,
                 String itemsIncludedMaintenanceFee, long netRentableArea,
                 long rentableArea,boolean parking,long parkingFee,Date moveInAvailableDate,
                 long size,String heatType,String estateType,long household,long roomCount,
                 Date usageAvailableDate,long bathCount,List<Asset> assets, Estate estate
    ) {//생성자
        this.deposit = deposit;
        this.totalFloors = totalFloors;
        this.size = size;
        this.usageAvailableDate = usageAvailableDate;
        this.itemsIncludedMaintenanceFee = itemsIncludedMaintenanceFee;
        this.rentableArea = rentableArea;
        this.parking = parking;
        this.netRentableArea = netRentableArea;
        this.monthlyRent = monthlyRent;
        this.shortTermRent = shortTermRent;
        this.heatType = heatType;
        this.moveInAvailableDate = moveInAvailableDate;
        this.assets = assets;
        this.maintenanceFee = maintenanceFee;
        this.currentFloors = currentFloors;
        this.roomCount = roomCount;
        this.estateType = estateType;
        this.bathCount = bathCount;
        this.household = household;
        this.parkingFee = parkingFee;
        this.sellingFee = sellingFee;
        this.estate = estate;
    }
}
