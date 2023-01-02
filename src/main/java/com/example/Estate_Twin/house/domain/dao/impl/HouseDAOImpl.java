package com.example.Estate_Twin.house.domain.dao.impl;

import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.house.domain.dao.HouseDAO;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Component
@AllArgsConstructor
public class HouseDAOImpl implements HouseDAO {
    private HouseRepository houseRepository;

    @Override
    @Transactional
    public House saveHouse(House house) {
        return houseRepository.save(house);
    }

    @Override
    public House findHouse(Long houseId) {
        return houseRepository.findById(houseId).orElseThrow(()->new CheckHouseException(ErrorCode.HOUSE_NOT_FOUND));
    }

    @Override
    @Transactional
    public House updateHouse(House house, HouseUpdateRequestDto dto) {
        return house.update(dto);
    }

    @Override
    public House findHouseByEstateId(Long estateId) {
        return houseRepository.findHouseByEstate_Id(estateId).orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND));
    }
}
