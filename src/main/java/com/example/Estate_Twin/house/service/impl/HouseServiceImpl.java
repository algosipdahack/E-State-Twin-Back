package com.example.Estate_Twin.house.service.impl;

import com.example.Estate_Twin.house.domain.dao.HouseDAO;
import com.example.Estate_Twin.house.service.HouseService;
import com.example.Estate_Twin.house.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseDAO houseDAO;

    @Override
    public HouseResponseDto getHouse(Long id) {
        return new HouseResponseDto(houseDAO.findHouse(id));
    }

    @Override
    public HouseResponseDto saveHouse(HouseSaveRequestDto houseSaveRequestDto) {
        return new HouseResponseDto(houseDAO.saveHouse(houseSaveRequestDto.toEntity()));
    }

    @Override
    public HouseResponseDto updateHouse(Long id, HouseUpdateRequestDto houseUpdateRequestDto) {
        return new HouseResponseDto(houseDAO.updateHouse(id, houseUpdateRequestDto.getDeposit(),
                houseUpdateRequestDto.getMonthlyRent(),
                houseUpdateRequestDto.getSellingFee(), houseUpdateRequestDto.getCurrentFloors(),
                houseUpdateRequestDto.getTotalFloors(), houseUpdateRequestDto.isParking(),
                houseUpdateRequestDto.getMaintenanceFee(), houseUpdateRequestDto.getHeatType(),
                houseUpdateRequestDto.getNetRentableArea(), houseUpdateRequestDto.getRentableArea(),
                houseUpdateRequestDto.isParking(), houseUpdateRequestDto.getParkingFee(),
                houseUpdateRequestDto.getMoveInAvailableDate(), houseUpdateRequestDto.getSize(),
                houseUpdateRequestDto.getHeatType(), houseUpdateRequestDto.getEstateType(),
                houseUpdateRequestDto.getHousehold(), houseUpdateRequestDto.getRoomCount(),
                houseUpdateRequestDto.getUsageAvailableDate(), houseUpdateRequestDto.getBathCount()));
    }
}
