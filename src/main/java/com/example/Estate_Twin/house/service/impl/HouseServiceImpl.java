package com.example.Estate_Twin.house.service.impl;

import com.example.Estate_Twin.house.domain.dao.HouseDAO;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.house.service.HouseService;
import com.example.Estate_Twin.house.web.dto.HouseResponseDto;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseDAO houseDAO;

    public HouseResponseDto getHouse(Long id) {
        return new HouseResponseDto(houseDAO.findHouse(id));
    }

    @Transactional
    public Long saveHouse(HouseSaveRequestDto houseSaveRequestDto) {
        return houseRepository.save(houseSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long updateHouse(Long id, HouseUpdateRequestDto houseUpdateRequestDto) {
        House house = houseRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 집이 없습니다. id = "+id));
        house.update(houseUpdateRequestDto.getDeposit(), houseUpdateRequestDto.getMonthlyRent(),
                houseUpdateRequestDto.getSellingFee(),houseUpdateRequestDto.getCurrentFloors(),
                houseUpdateRequestDto.getTotalFloors(), houseUpdateRequestDto.isShortTermRent(),
                houseUpdateRequestDto.getMaintenanceFee(), houseUpdateRequestDto.getItemsIncludedMaintenanceFee(),
                houseUpdateRequestDto.getNetRentableArea(), houseUpdateRequestDto.getRentableArea(),
                houseUpdateRequestDto.isParking(), houseUpdateRequestDto.getParkingFee(),
                houseUpdateRequestDto.getMoveInAvailableDate(), houseUpdateRequestDto.getSize(),
                houseUpdateRequestDto.getHeatType(), houseUpdateRequestDto.getEstateType(),
                houseUpdateRequestDto.getHousehold(), houseUpdateRequestDto.getRoomCount(),
                houseUpdateRequestDto.getUsageAvailableDate(), houseUpdateRequestDto.getBathCount()
        );
        return id;
    }
}
