package com.example.Estate_Twin.house.service;

import com.example.Estate_Twin.house.web.dto.*;

public interface HouseService {
    HouseResponseDto getHouse(Long id);
    HouseResponseDto saveHouse(HouseSaveRequestDto houseSaveRequestDto);
    HouseResponseDto updateHouse(Long id, HouseUpdateRequestDto houseUpdateRequestDto);
}
