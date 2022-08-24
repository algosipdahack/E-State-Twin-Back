package com.example.Estate_Twin.address.service;

import com.example.Estate_Twin.address.web.dto.*;

public interface AddressService {
    AddressResponseDto getAddress(Long id);
    AddressResponseDto saveAddress(AddressSaveRequestDto addressSaveRequestDto);
    AddressResponseDto updateAddress(Long id, AddressUpdateRequestDto addressUpdateRequestDto);
}
