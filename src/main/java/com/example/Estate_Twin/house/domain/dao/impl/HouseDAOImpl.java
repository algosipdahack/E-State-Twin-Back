package com.example.Estate_Twin.house.domain.dao.impl;

import com.example.Estate_Twin.house.domain.dao.HouseDAO;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@AllArgsConstructor
public class HouseDAOImpl implements HouseDAO {
    private HouseRepository houseRepository;

    @Override
    public House saveHouse(House house) {
        return houseRepository.save(house);
    }

    @Override
    public House findHouse(Long id) {
        return houseRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 집이 없습니다 id = "+id));
    }

    @Override
    public House updateHouse(Long id, HouseUpdateRequestDto dto) {
        return houseRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 집이 없습니다 id = "+id))
                .builder()
                .deposit(dto.getDeposit())
                .monthlyRent(dto.getMonthlyRent())
                .sellingFee(dto.getSellingFee())
                .currentFloors(dto.getCurrentFloors())
                .totalFloors(dto.getTotalFloors())
                .shortTermRent(dto.isShortTermRent())
                .maintenanceFee(dto.getMaintenanceFee())
                .itemsIncludedMaintenanceFee(dto.getItemsIncludedMaintenanceFee())
                .netRentableArea(dto.getNetRentableArea())
                .rentableArea(dto.getRentableArea())
                .parking(dto.isParking())
                .parkingFee(dto.getParkingFee())
                .moveInAvailableDate(dto.getMoveInAvailableDate())
                .size(dto.getSize())
                .heatType(dto.getHeatType())
                .estateType(dto.getEstateType())
                .household(dto.getHousehold())
                .roomCount(dto.getRoomCount())
                .usageAvailableDate(dto.getUsageAvailableDate())
                .bathCount(dto.getBathCount())
                .build();
    }
}
