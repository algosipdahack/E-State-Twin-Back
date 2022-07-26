package com.example.Estate_Twin.domain.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AssetRepository extends JpaRepository<Asset,Long> {
}
