package com.example.Estate_Twin.asset.domain.repository;

import com.example.Estate_Twin.asset.domain.Asset;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AssetRepository extends JpaRepository<Asset,Long> {
}
