package com.example.Estate_Twin.house.service.impl;

import com.example.Estate_Twin.house.domain.dao.impl.HouseDAOImpl;
import com.example.Estate_Twin.house.service.HouseService;
import com.example.Estate_Twin.house.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseDAOImpl houseDAO;

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
        return new HouseResponseDto(houseDAO.updateHouse(houseDAO.findHouse(id), houseUpdateRequestDto));
    }
}
