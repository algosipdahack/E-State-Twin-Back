package com.example.Estate_Twin.address.service;

import com.example.Estate_Twin.address.data.dao.impl.AddressDAOImpl;
import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.address.data.repository.AddressRepository;
import com.example.Estate_Twin.address.service.impl.AddressServiceImpl;
import com.example.Estate_Twin.address.web.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.Optional;

public class AddressServiceTest {
    private AddressDAOImpl addressDAO;
    private AddressServiceImpl addressService;
    private AddressRepository addressRepository = Mockito.mock(AddressRepository.class);
    Address address;

    @BeforeEach
    public void setUpTest() {
        addressDAO = new AddressDAOImpl(addressRepository);
        addressService = new AddressServiceImpl(addressDAO);
        address = new Address().builder()
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
        address.setId(1L);
    }

    @Test
    void getAddressTest() {
        Mockito.when(addressRepository.findById(1L))
                .thenReturn(Optional.of(address));

        AddressResponseDto addressResponseDto = addressService.getAddress(1L);
        Assertions.assertEquals(addressResponseDto.getBlock(),address.getBlock());
        verify(addressRepository).findById(1L);
    }

    @Test
    void saveAddressTest() {
        Mockito.when(addressRepository.save(any(Address.class)))
                .then(returnsFirstArg());
        AddressResponseDto addressResponseDto = addressService.saveAddress(new AddressSaveRequestDto(address.getCity(),address.getBorough(),address.getTown(),
                address.getComplexName(),address.getBlock(),address.getUnit(),address.getRoadName(),address.getMainBuildingNumber(),
                address.getSubBuildingNumber(),address.getBuildingName()));
        Assertions.assertEquals(addressResponseDto.getBlock(),address.getBlock());
        verify(addressRepository).save(any());
    }
}