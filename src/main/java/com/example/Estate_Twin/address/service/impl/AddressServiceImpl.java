package com.example.Estate_Twin.address.service.impl;

import com.example.Estate_Twin.address.data.dao.AddressDAO;
import com.example.Estate_Twin.address.data.repository.AddressRepository;
import com.example.Estate_Twin.address.service.AddressService;
import com.example.Estate_Twin.address.web.dto.AddressResponseDto;
import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.address.web.dto.AddressUpdateRequestDto;
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
        return null;
    }

    @Override
    public AddressResponseDto updateAddress(Long id, AddressUpdateRequestDto addressUpdateRequestDto) {
        return null;
    }
}
