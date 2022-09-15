package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.contractstate.domain.repository.ContractStateRepository;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.dao.impl.EstateDAOImpl;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.estate.domain.repository.EstateHitRepository;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

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
}