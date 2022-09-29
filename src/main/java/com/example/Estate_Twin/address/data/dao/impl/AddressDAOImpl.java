package com.example.Estate_Twin.address.data.dao.impl;

import com.example.Estate_Twin.address.data.dao.AddressDAO;
import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.address.data.repository.AddressRepository;
import com.example.Estate_Twin.address.web.dto.AddressUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressDAOImpl implements AddressDAO {
    private AddressRepository addressRepository;

    @Override
    public Address updateAddress(Long id, AddressUpdateRequestDto dto) {
        Address address = findAddress(id).builder()
                .city(dto.getCity())
                .borough(dto.getBorough())
                .town(dto.getTown())
                .complexName(dto.getComplexName())
                .block(dto.getBlock())
                .unit(dto.getUnit())
                .roadName(dto.getRoadName())
                .mainBuildingNumber(dto.getMainBuildingNumber())
                .subBuildingNumber(dto.getSubBuildingNumber())
                .buildingName(dto.getBuildingName())
                .build();
        return addressRepository.save(address);
    }

    @Override
    public Address findAddress(Long id) {
        return addressRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 주소가 존재하지 않습니다. id = "+ id));
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }
}
