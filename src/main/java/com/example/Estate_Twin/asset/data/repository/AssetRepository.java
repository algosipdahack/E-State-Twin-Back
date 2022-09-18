package com.example.Estate_Twin.asset.data.repository;

import com.example.Estate_Twin.asset.data.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AssetRepository extends JpaRepository<Asset,Long> , AssetRepositoryCustom{
    List<Asset> findAllByOrderByIdDesc();
}
