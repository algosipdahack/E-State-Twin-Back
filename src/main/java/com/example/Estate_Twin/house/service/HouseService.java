package com.example.Estate_Twin.house.service;

import com.example.Estate_Twin.house.domain.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.house.web.dto.HouseResponseDto;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;

    public HouseResponseDto findById(Long id) {
        House house = houseRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 집이 없습니다. id = "+id));
        return new HouseResponseDto(house);
    }

    @Transactional
    public Long save(HouseSaveRequestDto houseSaveRequestDto) {
        return houseRepository.save(houseSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, HouseUpdateRequestDto houseUpdateRequestDto) {
        House house = houseRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 집이 없습니다. id = "+id));
        house.update(houseUpdateRequestDto.getDeposit(), houseUpdateRequestDto.getMonthlyRent(),
                houseUpdateRequestDto.getSellingFee(),houseUpdateRequestDto.getCurrentFloors(),
                houseUpdateRequestDto.getTotalFloors(), houseUpdateRequestDto.isParking(),
                houseUpdateRequestDto.getMaintenanceFee(), houseUpdateRequestDto.getItemsIncludedMaintenanceFee(),
                houseUpdateRequestDto.getRentableArea(), houseUpdateRequestDto.getRentableArea(),
                houseUpdateRequestDto.isParking(), houseUpdateRequestDto.getParkingFee(),
                houseUpdateRequestDto.getMoveInAvailableDate(), houseUpdateRequestDto.getSize(),
                houseUpdateRequestDto.getHeatType(), houseUpdateRequestDto.getEstateType(),
                houseUpdateRequestDto.getHousehold(), houseUpdateRequestDto.getRoomCount(),
                houseUpdateRequestDto.getUsageAvailableDate(), houseUpdateRequestDto.getBathCount());
        return id;
    }
}
