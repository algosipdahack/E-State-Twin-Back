package com.example.Estate_Twin.house.web.dto;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.Extra;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.entity.Structure;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private String heatType;
    private EstateType estateType;
    private boolean elevator;
    private boolean duplex;
    @Schema(description = "방구조", example = "LOFT, SEPERATED_KITCHEN")
    private String structure;
    @Schema(description = "베란다", example = "VERANDA, BALCONY")
    private String veranda;
    public House toEntity() {
        return House.builder()
                .currentFloors(currentFloors)
                .deposit(deposit)
                .estateType(estateType)
                .heatType(heatType)
                .itemsIncludedMaintenanceFee(itemsIncludedMaintenanceFee)
                .maintenanceFee(maintenanceFee)
                .monthlyRent(monthlyRent)
                .moveInAvailableDate(moveInAvailableDate)
                .netRentableArea(netRentableArea)
                .parking(parking)
                .parkingFee(parkingFee)
                .rentableArea(rentableArea)
                .sellingFee(sellingFee)
                .totalFloors(totalFloors)
                .shortTermRent(shortTermRent)
                .elevator(elevator)
                .duplex(duplex)
                .structure(Structure.of(structure))
                .veranda(Extra.of(veranda))
                .build();
    }
}
