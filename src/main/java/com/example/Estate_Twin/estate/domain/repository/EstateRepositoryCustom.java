package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.estate.domain.entity.EstateHit;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.domain.entity.House;

import java.util.List;

public interface EstateRepositoryCustom {
    List<EstateMainDto> findByBoroughOrderByWeeklyHit(String borough);
    List<EstateListResponseDto> findEstateList(Long estateId, int pageSize);
    List<AssetResponseDto> findAssetList(Long estateId);
    EstateHit findEstateHit(Long estateId);
    House findHouse(Long estateId);
    List<EstateModeDto> findOwnerEstateList(Long userId);
    EstateModeDto findTenentEstateList(Long userId);
}
