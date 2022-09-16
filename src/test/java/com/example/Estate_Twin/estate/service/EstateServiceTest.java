package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.dao.impl.EstateDAOImpl;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.estate.domain.repository.EstateHitRepository;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.house.web.dto.HouseResponseDto;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class EstateServiceTest {
    private EstateRepository estateRepository = Mockito.mock(EstateRepository.class);
    private EstateHitRepository estateHitRepository = Mockito.mock(EstateHitRepository.class);
    private ContractStateRepository contractStateRepository = Mockito.mock(ContractStateRepository.class);
    private EstateDAOImpl estateDAO;
    private Estate estate;
    @BeforeEach
    void setUpTest() {
        estateDAO = new EstateDAOImpl(estateRepository,estateHitRepository,contractStateRepository);
        Address address = new Address().builder()
                .city("서울특별시")
                .borough("강남구")
                .town("개포동")
                .complexName("1단지")
                .block("1동")
                .unit("1호")
                .roadName("1로")
                .mainBuildingNumber(1)
                .subBuildingNumber(1)
                .buildingName("1건물")
                .build();
        estate = new Estate().builder()
                .estateThumbNail("src")
                .content("content")
                .model("model")
                .city("경기도")
                .borough("고양시")
                .town("화정동")
                .thumbnail3D("3D")
                .transactionType(TransactionType.LEASE)
                .address(address)
                .build();
        estate.setId(1L);
    }
    @Test
    void getHouseTest() {
        Mockito.when(estateRepository.findById(1L))
                .thenReturn(Optional.of(estate));

        EstateResponseDto estateResponseDto = estateDAO.getHouse(123L);
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
}