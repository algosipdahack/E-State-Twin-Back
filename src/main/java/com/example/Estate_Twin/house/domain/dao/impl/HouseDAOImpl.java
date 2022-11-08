package com.example.Estate_Twin.house.domain.dao.impl;

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
    public House findHouse(Long id) {
        return houseRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 집이 없습니다 id = "+id));
    }

    @Override
    @Transactional
    public House updateHouse(House house, HouseUpdateRequestDto dto) {
        return house.update(dto);
    }

    @Override
    public House findHouseByEstateId(Long estateId) {
        return houseRepository.findHouseByEstate_Id(estateId).orElseThrow(()-> new IllegalArgumentException("해당 아이디를 가진 estate가 없습니다!!"+estateId));
    }
}
