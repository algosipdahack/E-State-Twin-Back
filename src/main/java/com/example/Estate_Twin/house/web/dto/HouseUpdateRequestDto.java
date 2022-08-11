package com.example.Estate_Twin.house.web.dto;

import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.estate.domain.EstateType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private Date moveInAvailableDate;

    private Long size;

    private String heatType;

    private EstateType estateType;

    private Long household;

    private Date usageAvailableDate;

    private Long roomCount;

    private Long bathCount;

    private List<Asset> assets = new ArrayList<>();

    @Builder
    public HouseUpdateRequestDto( Long deposit, Long monthlyRent, Long sellingFee,
                                     Long currentFloors, Long totalFloors,
                                     boolean shortTermRent, Long maintenanceFee,
                                     String itemsIncludedMaintenanceFee, Long netRentableArea,
                                     Long rentableArea, boolean parking, Long parkingFee,
                                     Date moveInAvailableDate, Date usageAvailableDate, Long size, String heatType,
                                     EstateType estateType, Long household,
                                     Long roomCount, Long bathCount, List<Asset> assets) {
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
        this.assets = assets;
    }
}
