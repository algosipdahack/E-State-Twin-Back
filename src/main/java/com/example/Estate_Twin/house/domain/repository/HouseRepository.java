package com.example.Estate_Twin.house.domain.repository;

import com.example.Estate_Twin.house.domain.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House,Long> {
}
