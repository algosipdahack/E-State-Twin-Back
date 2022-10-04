package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.Preference;

public interface PreferEstateRepositoryCustom {
    boolean existsByEstateIdAndUserIdAndPrefer(Long estateId, Long userId, Preference prefer);
}
