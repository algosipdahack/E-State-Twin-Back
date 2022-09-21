package com.example.Estate_Twin.house.web.dto;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class HouseSaveRequestDto {
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
    public House toEntity() {
        return House.builder()
                .bathCount(bathCount)
                .currentFloors(currentFloors)
                .deposit(deposit)
                .estateType(estateType)
                .heatType(heatType)
                .household(household)
                .itemsIncludedMaintenanceFee(itemsIncludedMaintenanceFee)
                .maintenanceFee(maintenanceFee)
                .monthlyRent(monthlyRent)
                .moveInAvailableDate(moveInAvailableDate)
                .netRentableArea(netRentableArea)
                .parking(parking)
                .parkingFee(parkingFee)
                .rentableArea(rentableArea)
                .roomCount(roomCount)
                .sellingFee(sellingFee)
                .totalFloors(totalFloors)
                .shortTermRent(shortTermRent)
                .size(size)
                .usageAvailableDate(usageAvailableDate)
                .build();

    }
}
