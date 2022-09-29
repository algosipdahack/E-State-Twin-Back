package com.example.Estate_Twin.address.data.dao;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.address.web.dto.AddressUpdateRequestDto;


public interface AddressDAO {
    Address updateAddress(Long id, AddressUpdateRequestDto dto);
    Address findAddress(Long id);
    Address saveAddress(Address address);
}
