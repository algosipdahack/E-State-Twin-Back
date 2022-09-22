package com.example.Estate_Twin.house.service;

import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.house.domain.dao.impl.HouseDAOImpl;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.house.service.impl.HouseServiceImpl;
import com.example.Estate_Twin.house.web.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/*
public class HouseServiceTest {
    private HouseRepository houseRepository = Mockito.mock(HouseRepository.class);
    private HouseDAOImpl houseDAO;
    private HouseServiceImpl houseService;
    private House givenHouse;
    @BeforeEach
    public void setUpTest() {
        houseDAO =  new HouseDAOImpl(houseRepository);
        houseService = new HouseServiceImpl(houseDAO);
        givenHouse = new House().builder()
                .bathCount(1L)
                .usageAvailableDate(LocalDateTime.now())
                .roomCount(1L)
                .household(1L)
                .estateType(EstateType.APARTMENT)
                .heatType("heattype")
                .size(1L)
                .moveInAvailableDate(LocalDateTime.now())
                .parkingFee(1L)
                .parking(true)
                .rentableArea(1L)
                .netRentableArea(1L)
                .itemsIncludedMaintenanceFee("fee")
                .maintenanceFee(1L)
                .shortTermRent(true)
                .totalFloors(1L)
                .currentFloors(1L)
                .sellingFee(1L)
                .monthlyRent(1L)
                .deposit(1L)
                .monthlyRent(1L)
                .build();
        givenHouse.setId(123L);
    }

    @Test
    void getHouseTest() {
        Mockito.when(houseRepository.findById(123L))
                .thenReturn(Optional.of(givenHouse));

        HouseResponseDto houseResponseDto = houseService.getHouse(123L);
        Assertions.assertEquals(houseResponseDto.getBathCount(),givenHouse.getBathCount());
        System.out.println(houseResponseDto.getHeatType());
        verify(houseRepository).findById(123L); // 해당 함수가 호출되었는지를 검증
    }

    @Test
    void saveHouseTest() {
        Mockito.when(houseRepository.save(any(House.class)))
                .then(returnsFirstArg());
        HouseResponseDto houseResponseDto = houseService.saveHouse(new HouseSaveRequestDto(givenHouse.getDeposit(),givenHouse.getMonthlyRent(),givenHouse.getSellingFee(),
        givenHouse.getCurrentFloors(),givenHouse.getTotalFloors(),givenHouse.isShortTermRent(),givenHouse.getMaintenanceFee(),
                givenHouse.getItemsIncludedMaintenanceFee(),givenHouse.getNetRentableArea(),givenHouse.getRentableArea(),
                givenHouse.isParking(),givenHouse.getParkingFee(),givenHouse.getMoveInAvailableDate(),givenHouse.getUsageAvailableDate(),
                givenHouse.getSize(),givenHouse.getHeatType(),givenHouse.getEstateType(),givenHouse.getHousehold(),givenHouse.getRoomCount(),
                givenHouse.getBathCount()));
        Assertions.assertEquals(houseResponseDto.getBathCount(),givenHouse.getBathCount());
        verify(houseRepository).save(any());
    }

*/