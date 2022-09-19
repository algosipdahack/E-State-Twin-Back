package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.estate.web.dto.EstateHitDto;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import com.example.Estate_Twin.estate.web.dto.EstateMainDto;
import com.example.Estate_Twin.house.web.dto.HouseDto;

import java.util.List;

public interface EstateRepositoryCustom {
    List<EstateMainDto> findByBoroughOrderByWeeklyHit(String borough);
    List<EstateListResponseDto> findEstateList();
    List<AssetResponseDto> findAssetList(Long estateId);
    AddressDto findAddress(Long estateId);
    EstateHitDto findEstateHit(Long estateId);
    HouseDto findHouse(Long estateId);

}
