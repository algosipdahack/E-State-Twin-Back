package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.estate.domain.entity.Preference;
import com.example.Estate_Twin.estate.web.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PreferEstateService {
    PreferEstateResponseDto savePreferEstate(Long estateId, String email, Preference prefer);
    List<EstateListResponseDto> getPreferEstate(String email, Preference prefer, Pageable pageable);
}
