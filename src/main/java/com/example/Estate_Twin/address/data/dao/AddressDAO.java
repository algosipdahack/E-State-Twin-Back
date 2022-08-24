package com.example.Estate_Twin.address.data.dao;

import com.example.Estate_Twin.address.data.entity.Address;


public interface AddressDAO {
    Address updateAddress(Long id, String city, String borough, String town, String complexName,
                        String block, String unit, String roadName,
                        int mainBuildingNumber, int subBuildingNumber, String buildingName);
    Address findAddress(Long id);
    Address saveAddress(Address address);
}
