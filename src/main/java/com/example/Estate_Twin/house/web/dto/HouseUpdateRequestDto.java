package com.example.Estate_Twin.house.web.dto;

import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime moveInAvailableDate;
    private String heatType;
    private EstateType estateType;
    private boolean elevator;
    private boolean duplex;
    private String structure;
    private boolean veranda;
}
