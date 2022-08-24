package com.example.Estate_Twin.address.data.dao.impl;

import com.example.Estate_Twin.address.data.dao.AddressDAO;
import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.address.data.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressDAOImpl implements AddressDAO {
    private AddressRepository addressRepository;

    @Override
    public Address updateAddress(Long id, String city, String borough, String town,
                               String complexName, String block, String unit,
                               String roadName, int mainBuildingNumber, int subBuildingNumber,
                               String buildingName) {
        Address address = findAddress(id).builder()
                .city(city)
                .borough(borough)
                .town(town)
                .complexName(complexName)
                .block(block)
                .unit(unit)
                .roadName(roadName)
                .mainBuildingNumber(mainBuildingNumber)
                .subBuildingNumber(subBuildingNumber)
                .buildingName(buildingName)
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
