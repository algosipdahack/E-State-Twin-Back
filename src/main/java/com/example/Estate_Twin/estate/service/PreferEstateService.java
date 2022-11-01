package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.estate.domain.entity.Preference;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.user.domain.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PreferEstateService {
    PreferEstateResponseDto savePreferEstate(Long estateId, User user, Preference prefer);
    List<EstateListResponseDto> getPreferEstate(User user, Preference prefer, Pageable pageable);
}
