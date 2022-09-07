package com.example.Estate_Twin.address.service.impl;

import com.example.Estate_Twin.address.data.dao.AddressDAO;
import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.address.service.AddressService;
import com.example.Estate_Twin.address.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressDAO addressDAO;

    @Override
    public AddressResponseDto getAddress(Long id) {
        return new AddressResponseDto(addressDAO.findAddress(id));
    }

    @Override
    public AddressResponseDto saveAddress(AddressSaveRequestDto addressSaveRequestDto) {
        return new AddressResponseDto(addressDAO.saveAddress(addressSaveRequestDto.toEntity()));
    }

    @Override
    public AddressResponseDto updateAddress(Long id, AddressUpdateRequestDto addressUpdateRequestDto) {
        Address address = addressDAO.updateAddress(id, addressUpdateRequestDto.getCity(), addressUpdateRequestDto.getBorough(),
                addressUpdateRequestDto.getTown(), addressUpdateRequestDto.getComplexName(), addressUpdateRequestDto.getBlock(),
                addressUpdateRequestDto.getUnit(), addressUpdateRequestDto.getRoadName(), addressUpdateRequestDto.getMainBuildingNumber(),
                addressUpdateRequestDto.getSubBuildingNumber(), addressUpdateRequestDto.getBuildingName());
        return new AddressResponseDto(address);
    }
}
