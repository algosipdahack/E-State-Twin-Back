package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.estate.domain.entity.Preference;
import com.example.Estate_Twin.estate.web.dto.PreferEstateResponseDto;

public interface PreferEstateService {
    PreferEstateResponseDto preferEstate(Long estateId, String email, Preference prefer);
}
