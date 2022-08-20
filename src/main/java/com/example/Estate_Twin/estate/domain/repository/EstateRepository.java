package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.Estate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstateRepository extends JpaRepository<Estate,Long> {
    List<Estate> findAllByOrderByIdDesc();
}
