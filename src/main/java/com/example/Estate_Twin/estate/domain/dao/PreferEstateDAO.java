package com.example.Estate_Twin.estate.domain.dao;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import com.example.Estate_Twin.user.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PreferEstateDAO {
    PreferEstate savePreferEstate(Estate estate, User user, Preference prefer);
    boolean existPreferEstate(Long estateId, Long userId, Preference prefer);
    Page<EstateListResponseDto> findPreferEstate(Long userId, Preference prefer, Pageable pageable);
    List<PreferEstate> findPreferEstatesByEstateId(Long estateId);
}
