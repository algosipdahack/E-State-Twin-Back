package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.Estate;

import java.util.List;

public interface EstateRepositoryCustom {
    List<Estate> findByBoroughOrderByWeeklyHit(String borough);
}
