package com.example.Estate_Twin.house.domain.dao;

import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;

public interface HouseDAO {
    House saveHouse(House house);
    House findHouse(Long id);
    House updateHouse(House house, HouseUpdateRequestDto dto);
}
