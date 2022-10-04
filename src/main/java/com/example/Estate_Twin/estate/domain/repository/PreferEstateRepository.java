package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.PreferEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferEstateRepository extends JpaRepository<PreferEstate, Long>, PreferEstateRepositoryCustom {
}
