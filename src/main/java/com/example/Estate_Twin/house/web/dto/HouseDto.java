package com.example.Estate_Twin.house.web.dto;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class HouseDto {
    private final Long deposit;
    private final Long monthlyRent;
    private final Long sellingFee;
    private final Long currentFloors;
    private final Long totalFloors;
    private final boolean shortTermRent;
    private final Long maintenanceFee;
    private final String itemsIncludedMaintenanceFee;
    private final Long netRentableArea;
    private final Long rentableArea;
    private final boolean parking;
    private final Long parkingFee;
    private final LocalDateTime moveInAvailableDate;
    private final Long size;
    private final String heatType;
    private final EstateType estateType;
    private final Long household;
    private final LocalDateTime usageAvailableDate;
    private final Long roomCount;
    private final Long bathCount;
    @QueryProjection
    public HouseDto(House house) {
        this.deposit = house.getDeposit();
        this.monthlyRent = house.getMonthlyRent();
        this.sellingFee = house.getSellingFee();
        this.currentFloors = house.getCurrentFloors();
        this.totalFloors = house.getTotalFloors();
        this.shortTermRent = house.isShortTermRent();
        this.maintenanceFee = house.getMaintenanceFee();
        this.itemsIncludedMaintenanceFee = house.getItemsIncludedMaintenanceFee();
        this.netRentableArea = house.getNetRentableArea();
        this.rentableArea = house.getRentableArea();
        this.parking = house.isParking();
        this.parkingFee = house.getParkingFee();
        this.moveInAvailableDate = house.getMoveInAvailableDate();
        this.size = house.getSize();
        this.heatType = house.getHeatType();
        this.estateType = house.getEstateType();
        this.household = house.getHousehold();
        this.usageAvailableDate = house.getUsageAvailableDate();
        this.roomCount = house.getRoomCount();
        this.bathCount = house.getBathCount();
    }
}
