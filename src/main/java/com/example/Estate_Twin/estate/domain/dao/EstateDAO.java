package com.example.Estate_Twin.estate.domain.dao;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.user.domain.entity.*;

public interface EstateDAO {
    Estate saveEstate(Estate estate, House house);
    Estate allowBroker(Estate estate, Broker broker);
    Estate allowOwner(Estate estate, User owner);
    Estate getEstate(Long id);
    boolean checkEnroll(Estate estate);
    Estate saveFirst(Broker broker, User owner, Address address);
    Estate matchTenant(Long estateId, User user);

}
