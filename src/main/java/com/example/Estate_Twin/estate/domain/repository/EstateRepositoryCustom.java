package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.estate.domain.entity.EstateHit;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.user.domain.entity.Broker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EstateRepositoryCustom {
    List<EstateMainDto> findByBoroughOrderByWeeklyHit(String borough);
    Page<EstateListResponseDto> findEstateList(Long estateId, Pageable pageable);
    List<AssetResponseDto> findAssetList(Long estateId);
    EstateHit findEstateHit(Long estateId);
    House findHouse(Long estateId);
    List<EstateModeDto> findOwnerEstateList(Long userId);
    EstateModeDto findTenantEstateList(Long userId);
}
