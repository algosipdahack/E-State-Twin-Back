package com.example.Estate_Twin.house.domain.dao;

import com.example.Estate_Twin.checklist.data.entity.Category;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.data.entity.RepairType;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.house.domain.entity.House;

import java.time.LocalDateTime;

public interface HouseDAO {
    House saveHouse(House house);
    House findHouse(Long id);
    House updateHouse(Long id, Long deposit, Long monthlyRent, Long sellingFee, Long currentFloors,
                          Long totalFloors, boolean shortTermRent, Long maintenanceFee,
                          String itemsIncludedMaintenanceFee, Long netRentableArea,
                          Long rentableArea, boolean parking, Long parkingFee, LocalDateTime moveInAvailableDate,
                          Long size, String heatType, EstateType estateType, Long household, Long roomCount,
                          LocalDateTime usageAvailableDate, Long bathCount);
}
