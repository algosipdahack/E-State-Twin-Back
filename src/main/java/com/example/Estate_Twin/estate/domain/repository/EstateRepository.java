package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.Estate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstateRepository extends JpaRepository<Estate,Long> {
}