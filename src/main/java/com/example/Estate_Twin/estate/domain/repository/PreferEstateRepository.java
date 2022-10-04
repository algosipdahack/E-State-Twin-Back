package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreferEstateRepository extends JpaRepository<PreferEstate, Long>, PreferEstateRepositoryCustom {
    @Query("select new com.example.Estate_Twin.estate.web.dto.EstateListResponseDto" +
            "(e.id, e.transactionType, e.estateThumbNail, e.address.town, h.estateType, e.address.buildingName, h.currentFloors, h.rentableArea, e.state, h.sellingFee) " +
            "from PreferEstate p " +
            "inner join Estate e ON e.id = p.estate.id " +
            "inner join House h ON h.id = e.house.id " +
            "where p.user.id = :userId and p.preference = :prefer")
    List<EstateListResponseDto> findByUserIdAndPrefer(@Param("userId") Long userId, @Param("prefer") Preference prefer);
}
