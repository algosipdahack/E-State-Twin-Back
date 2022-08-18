package com.example.Estate_Twin.house.service;

import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.house.web.dto.HouseResponseDto;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface HouseService {
    HouseResponseDto getHouse(Long id);
    Long saveHouse(HouseSaveRequestDto houseSaveRequestDto);
    Long updateHouse(Long id, HouseUpdateRequestDto houseUpdateRequestDto);
}
