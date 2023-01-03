package com.example.Estate_Twin.house.service.impl;

import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.house.service.HouseService;
import com.example.Estate_Twin.house.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private HouseRepository houseRepository;

    @Override
    public HouseResponseDto getHouse(Long houseId) {
        return new HouseResponseDto(houseRepository.findById(houseId).orElseThrow(()->new CheckHouseException(ErrorCode.HOUSE_NOT_FOUND)));
    }

    @Override
    public HouseResponseDto saveHouse(HouseSaveRequestDto houseSaveRequestDto) {
        return new HouseResponseDto(houseRepository.save(houseSaveRequestDto.toEntity()));
    }

    @Override
    public HouseResponseDto updateHouse(Long houseId, HouseUpdateRequestDto houseUpdateRequestDto) {
        return new HouseResponseDto(houseRepository.findById(houseId).orElseThrow(()->new CheckHouseException(ErrorCode.HOUSE_NOT_FOUND)).update(houseUpdateRequestDto));
    }
}
