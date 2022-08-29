package com.example.Estate_Twin.estate.domain.dao;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.media.domain.entity.Media;

import java.util.List;

public interface EstateDAO {
    Estate saveEstate(Estate estate, House house, Address address);
    Estate findEstate(Long id);
    Estate updateEstate(Long id, String content, String model,
                        ContractState contractState, TransactionType transactionType, String estateThumbNail,
                        String city, String borough, String thumbnail3D);
    Estate addEstateMedia(Long id, List<Media> mediaList);
    List<Estate> findEstateCustomized(String borough);
    List<Estate> findAllEstate();
}
