package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PreferEstateRepository extends JpaRepository<PreferEstate, Long>, PreferEstateRepositoryCustom {
    Optional<List<PreferEstate>> findPreferEstatesByEstate_Id(Long estateId);
}
