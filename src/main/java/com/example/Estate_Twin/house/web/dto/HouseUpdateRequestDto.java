package com.example.Estate_Twin.house.web.dto;

import com.example.Estate_Twin.estate.domain.entity.EstateType;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class HouseUpdateRequestDto {
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

    @Builder
    public HouseUpdateRequestDto(Long deposit, Long monthlyRent, Long sellingFee,
                               Long currentFloors, Long totalFloors,
                               boolean shortTermRent, Long maintenanceFee,
                               String itemsIncludedMaintenanceFee, Long netRentableArea,
                               Long rentableArea, boolean parking, Long parkingFee,
                               LocalDateTime moveInAvailableDate, LocalDateTime usageAvailableDate,
                               Long size, String heatType, EstateType estateType, Long household,
                               Long roomCount, Long bathCount) {
        this.deposit = deposit;
        this.monthlyRent = monthlyRent;
        this.sellingFee = sellingFee;
        this.currentFloors = currentFloors;
        this.totalFloors = totalFloors;
        this.shortTermRent = shortTermRent;
        this.maintenanceFee = maintenanceFee;
        this.itemsIncludedMaintenanceFee = itemsIncludedMaintenanceFee;
        this.netRentableArea = netRentableArea;
        this.rentableArea = rentableArea;
        this.parking = parking;
        this.parkingFee = parkingFee;
        this.moveInAvailableDate = moveInAvailableDate;
        this.size = size;
        this.heatType = heatType;
        this.estateType = estateType;
        this.household = household;
        this.usageAvailableDate = usageAvailableDate;
        this.roomCount = roomCount;
        this.bathCount = bathCount;
    }
}
