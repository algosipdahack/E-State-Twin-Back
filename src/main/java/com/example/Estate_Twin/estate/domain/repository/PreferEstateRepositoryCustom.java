package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.Preference;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PreferEstateRepositoryCustom {
    boolean existsByEstateIdAndUserIdAndPrefer(Long estateId, Long userId, Preference prefer);
    Page<EstateListResponseDto> findByUserIdAndPrefer(Long userId, Preference prefer, Pageable pageable);
}
