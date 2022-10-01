package com.example.Estate_Twin.estate.domain.dao;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import com.example.Estate_Twin.estate.web.dto.EstateMainDto;
import com.example.Estate_Twin.estate.web.dto.EstateUpdateRequestDto;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.user.domain.entity.Broker;
import com.example.Estate_Twin.user.domain.entity.User;

import java.util.List;

public interface EstateDAO {
    Estate saveEstate(Estate estate);
    Estate findEstate(Long id);
    Estate updateEstate(Long id, EstateUpdateRequestDto estateUpdateRequestDto);
    List<EstateMainDto> findEstateCustomized(String borough);
    List<EstateListResponseDto> findAllEstateList();
    Estate allowBroker(Estate estate, Broker broker);
    Estate allowOwner(Estate estate, User owner);
    Estate getEstate(Long id);
    House findHouse(Long id);
    EstateHit findEstateHit(Long id);
    List<AssetResponseDto> findAssets(Long id);
    Estate checkEnroll(Estate estate);
    Estate saveFirst(Broker broker, User owner, Address address);

}
