package com.example.Estate_Twin.house.domain.repository;

import com.example.Estate_Twin.house.domain.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House,Long> {
    Optional<House> findHouseByEstate_Id(Long estateId);
}
