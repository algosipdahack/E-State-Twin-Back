package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.EstateHit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstateHitRepository extends JpaRepository<EstateHit,Long> {
    Optional<EstateHit> findEstateHitByEstate_Id(Long estateId);
}
