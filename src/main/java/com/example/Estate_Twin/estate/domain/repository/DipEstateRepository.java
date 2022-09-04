package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.DipRecentEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipEstateRepository extends JpaRepository<DipRecentEstate, Long> {
}
