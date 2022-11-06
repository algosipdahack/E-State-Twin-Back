package com.example.Estate_Twin.house.domain.entity;


import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate moveInAvailableDate;
    private String heatType;
    private boolean elevator;
    private boolean duplex;
    private boolean veranda;
    @Enumerated(EnumType.STRING)
    private EstateType estateType;
    @Enumerated(EnumType.STRING)
    private Structure structure;
    @OneToOne(mappedBy = "house")
    private Estate estate;

    @Builder // 빌더 형태로 만들어줌
    public House(Long deposit, Long monthlyRent, Long sellingFee, Long currentFloors,
                 Long totalFloors, boolean shortTermRent, Long maintenanceFee,
                 String itemsIncludedMaintenanceFee, Long netRentableArea,
                 Long rentableArea, boolean parking, Long parkingFee, LocalDate moveInAvailableDate,
                 String heatType, EstateType estateType, boolean elevator, boolean duplex,
                 Structure structure, boolean veranda) {
        this.deposit = deposit;
        this.totalFloors = totalFloors;
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
        this.estateType = estateType;
        this.parkingFee = parkingFee;
        this.sellingFee = sellingFee;
        this.elevator = elevator;
        this.duplex = duplex;
        this.structure = structure;
        this.veranda = veranda;
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
        this.heatType = dto.getHeatType();
        this.estateType = dto.getEstateType();
        this.elevator = dto.isElevator();
        this.duplex = dto.isDuplex();
        this.structure = Structure.of(dto.getStructure());
        this.veranda = dto.isVeranda();
        return this;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
        this.estateType = EstateType.APARTMENT;
    }

    public void setOfficetel() {
        this.estateType = EstateType.OFFICETELS;
    }

}
