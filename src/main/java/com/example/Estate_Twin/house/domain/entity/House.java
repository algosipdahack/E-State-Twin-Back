package com.example.Estate_Twin.house.domain.entity;


import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
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

    @OneToOne(mappedBy = "house")
    private Estate estate;


    @Builder // 빌더 형태로 만들어줌
    public House(Long deposit, Long monthlyRent, Long sellingFee, Long currentFloors,
                 Long totalFloors, boolean shortTermRent, Long maintenanceFee,
                 String itemsIncludedMaintenanceFee, Long netRentableArea,
                 Long rentableArea,boolean parking,Long parkingFee,LocalDateTime moveInAvailableDate,
                 Long size,String heatType,EstateType estateType,Long household,Long roomCount,
                 LocalDateTime usageAvailableDate,Long bathCount
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
    }
    public void setId(Long id) { this.id = id; }
    public void setEstate(Estate estate) {
        this.estate = estate;
    }
}
