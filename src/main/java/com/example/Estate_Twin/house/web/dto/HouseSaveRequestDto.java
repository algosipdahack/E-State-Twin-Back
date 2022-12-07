package com.example.Estate_Twin.house.web.dto;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate moveInAvailableDate;
    private String heatType;
    private EstateType estateType;
    private boolean elevator;
    private boolean duplex;
    @Schema(description = "방구조", example = "LOFT, SEPARATE_KITCHEN")
    private String structure;
    private boolean veranda;
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
                .veranda(veranda)
                .build();
    }
}
