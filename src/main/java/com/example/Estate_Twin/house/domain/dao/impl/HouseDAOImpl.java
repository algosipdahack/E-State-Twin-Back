package com.example.Estate_Twin.house.domain.dao.impl;

import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.house.domain.dao.HouseDAO;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
    public House updateHouse(Long id, Long deposit, Long monthlyRent, Long sellingFee, Long currentFloors, Long totalFloors,
                             boolean shortTermRent, Long maintenanceFee, String itemsIncludedMaintenanceFee, Long netRentableArea,
                             Long rentableArea, boolean parking, Long parkingFee, LocalDateTime moveInAvailableDate, Long size,
                             String heatType, EstateType estateType, Long household, Long roomCount, LocalDateTime usageAvailableDate,
                             Long bathCount) {
        House newHouse = houseRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 집이 없습니다 id = "+id))
                .builder()
                .deposit(deposit)
                .monthlyRent(monthlyRent)
                .sellingFee(sellingFee)
                .currentFloors(currentFloors)
                .totalFloors(totalFloors)
                .shortTermRent(shortTermRent)
                .maintenanceFee(maintenanceFee)
                .itemsIncludedMaintenanceFee(itemsIncludedMaintenanceFee)
                .netRentableArea(netRentableArea)
                .rentableArea(rentableArea)
                .parking(parking)
                .parkingFee(parkingFee)
                .moveInAvailableDate(moveInAvailableDate)
                .size(size)
                .heatType(heatType)
                .estateType(estateType)
                .household(household)
                .roomCount(roomCount)
                .usageAvailableDate(usageAvailableDate)
                .bathCount(bathCount)
                .build();
        return houseRepository.save(newHouse);
    }
}
