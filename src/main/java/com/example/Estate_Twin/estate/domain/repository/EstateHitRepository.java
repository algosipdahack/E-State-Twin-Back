package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.EstateHit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface EstateHitRepository extends JpaRepository<EstateHit,Long> {
    Optional<EstateHit> findEstateHitByEstate_Id(Long estateId);
    @Query(value = "select e from EstateHit e where e.estate.id = :estateId")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<EstateHit> findWithPessimisticLockById(@Param("estateId") Long estateId);
}
