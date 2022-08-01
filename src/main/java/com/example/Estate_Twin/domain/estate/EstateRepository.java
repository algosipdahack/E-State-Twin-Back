package com.example.Estate_Twin.domain.estate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface EstateRepository extends JpaRepository<Estate,Long> {


}
