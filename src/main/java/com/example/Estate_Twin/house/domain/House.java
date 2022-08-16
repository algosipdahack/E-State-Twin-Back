package com.example.Estate_Twin.house.domain;


import com.example.Estate_Twin.estate.domain.EstateType;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.estate.domain.Estate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "house")
public class House extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long id;

    @Column
    private Long deposit;

    @Column
    private Long monthlyRent;

    @Column
    private Long sellingFee;

    @Column
    private Long currentFloors;

    @Column
    private Long totalFloors;

    @Column
    private boolean shortTermRent;

    @Column
    private Long maintenanceFee;

    @Column
    private String itemsIncludedMaintenanceFee;

    @Column
    private Long netRentableArea;

    @Column
    private Long rentableArea;

    @Column
    private boolean parking;

    @Column
    private Long parkingFee;

    @Column
    private LocalDateTime moveInAvailableDate;

    @Column
    private Long size;

    @Column
    private String heatType;

    @Column
    private EstateType estateType;

    @Column
    private Long household;

    @Column
    private LocalDateTime usageAvailableDate;

    @Column
    private Long roomCount;

    @Column
    private Long bathCount;

    @OneToMany(
            mappedBy = "house",
            cascade = {CascadeType.ALL},
            orphanRemoval = true // DB에서 함께 삭제됨
    )
    @JsonIgnore
    private List<Asset> assets = new ArrayList<>();

    @OneToOne(mappedBy = "house")
    private Estate estate;


    @Builder // 빌더 형태로 만들어줌
    public House(Long deposit, Long monthlyRent, Long sellingFee, Long currentFloors,
                 Long totalFloors, boolean shortTermRent, Long maintenanceFee,
                 String itemsIncludedMaintenanceFee, Long netRentableArea,
                 Long rentableArea,boolean parking,Long parkingFee,LocalDateTime moveInAvailableDate,
                 Long size,String heatType,EstateType estateType,Long household,Long roomCount,
                 LocalDateTime usageAvailableDate,Long bathCount,Estate estate
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

    public void update(Long deposit, Long monthlyRent, Long sellingFee, Long currentFloors,
                       Long totalFloors, boolean shortTermRent, Long maintenanceFee,
                       String itemsIncludedMaintenanceFee, Long netRentableArea,
                       Long rentableArea,boolean parking, Long parkingFee, LocalDateTime moveInAvailableDate,
                       Long size, String heatType, EstateType estateType, Long household, Long roomCount,
                       LocalDateTime usageAvailableDate, Long bathCount) {
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
        this.maintenanceFee = maintenanceFee;
        this.currentFloors = currentFloors;
        this.roomCount = roomCount;
        this.estateType = estateType;
        this.bathCount = bathCount;
        this.household = household;
        this.parkingFee = parkingFee;
        this.sellingFee = sellingFee;
    }



    public void addAsset(List<Asset> assets) {
        for(Asset asset : assets) {
            asset.setHouse(this);
        }
    }
}
