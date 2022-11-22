package com.example.Estate_Twin.house.web.dto;

import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.entity.Structure;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate moveInAvailableDate;
    private String heatType;
    private String estateType;
    private boolean elevator;
    private boolean duplex;
    private String structure;
    private boolean veranda;
    public House toEntity() {
        return House.builder()
                .duplex(duplex)
                .elevator(elevator)
                .monthlyRent(monthlyRent)
                .sellingFee(sellingFee)
                .currentFloors(currentFloors)
                .deposit(deposit)
                .totalFloors(totalFloors)
                .shortTermRent(shortTermRent)
                .maintenanceFee(maintenanceFee)
                .itemsIncludedMaintenanceFee(itemsIncludedMaintenanceFee)
                .rentableArea(rentableArea)
                .netRentableArea(netRentableArea)
                .parking(parking)
                .parkingFee(parkingFee)
                .estateType(EstateType.of(estateType))
                .structure(Structure.of(structure))
                .veranda(veranda)
                .heatType(heatType)
                .build();
    }
}
