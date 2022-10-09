package com.example.Estate_Twin.house.domain.entity;


import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "house")
public class House extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long id;
    private Long deposit;
    private Long monthlyRent;
    private Long sellingFee;
    private Long currentFloors;
    private Long totalFloors;
    private boolean shortTermRent;
    private Long maintenanceFee;
    private String itemsIncludedMaintenanceFee;
    private Long netRentableArea;
    private Long rentableArea;
    private boolean parking;
    private Long parkingFee;
    private LocalDateTime moveInAvailableDate;
    private Long size;
    private String heatType;
    private EstateType estateType;
    private Long household;
    private LocalDateTime usageAvailableDate;
    private Long roomCount;
    private Long bathCount;
    private boolean isOfficetel;
    @OneToOne(mappedBy = "house")
    private Estate estate;

    @Builder // 빌더 형태로 만들어줌
    public House(Long deposit, Long monthlyRent, Long sellingFee, Long currentFloors,
                 Long totalFloors, boolean shortTermRent, Long maintenanceFee,
                 String itemsIncludedMaintenanceFee, Long netRentableArea,
                 Long rentableArea,boolean parking,Long parkingFee,LocalDateTime moveInAvailableDate,
                 Long size,String heatType,EstateType estateType,Long household,Long roomCount,
                 LocalDateTime usageAvailableDate,Long bathCount)
    {
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

    public House update(HouseUpdateRequestDto dto) {
        this.deposit = dto.getDeposit();
        this.monthlyRent = dto.getMonthlyRent();
        this.sellingFee = dto.getSellingFee();
        this.currentFloors = dto.getCurrentFloors();
        this.totalFloors = dto.getTotalFloors();
        this.shortTermRent = dto.isShortTermRent();
        this.maintenanceFee = dto.getMaintenanceFee();
        this.itemsIncludedMaintenanceFee = dto.getItemsIncludedMaintenanceFee();
        this.netRentableArea = dto.getNetRentableArea();
        this.rentableArea = dto.getRentableArea();
        this.parking = dto.isParking();
        this.parkingFee = dto.getParkingFee();
        this.moveInAvailableDate = dto.getMoveInAvailableDate();
        this.size = dto.getSize();
        this.heatType = dto.getHeatType();
        this.estateType = dto.getEstateType();
        this.household = dto.getHousehold();
        this.usageAvailableDate = dto.getUsageAvailableDate();
        this.roomCount = dto.getRoomCount();
        this.bathCount = dto.getBathCount();
        return this;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public void setIsOfficetel() {
        this.isOfficetel = true;
    }

    @PrePersist
    public void prePersist() {
        this.isOfficetel = false;
    }
}
