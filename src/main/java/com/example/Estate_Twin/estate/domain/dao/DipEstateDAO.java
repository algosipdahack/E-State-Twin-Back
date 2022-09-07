package com.example.Estate_Twin.estate.domain.dao;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;

public interface DipEstateDAO {
    DipEstate saveEstate(Estate estate, House house, Address address);
    Estate findRecentEstate(Long id);
    Estate findDipEstate(Long id);
}
