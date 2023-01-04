package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.domain.entity.House;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstateRepositoryCustom {
    List<EstateMainDto> findByBoroughOrderByWeeklyHit(String borough);
    Page<EstateListResponseDto> findEstateList(Long estateId, Pageable pageable);
    House findHouseByEstateId(Long estateId);
    EstateModeDto findTenantEstateList(Long userId);
    List<EstateListResponseDto> findEstateByBorough(@Param("borough") String borough, Pageable pageable);
    List<EstateListResponseDto> findEstateByTown(@Param("town") String town, Pageable pageable);
    List<EstateModeDto> findOwnerEstateList(@Param("userId") Long userId);
}
