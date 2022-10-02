package com.example.Estate_Twin.asset.data.repository;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface AssetRepository extends JpaRepository<Asset,Long> , AssetRepositoryCustom{
    List<Asset> findAllByOrderByIdDesc();
    @Query("select a from Asset a join fetch a.checkLists")
    List<Asset> findAllUsingFetchJoin();

    @Query("select a from Asset a join fetch a.checkLists where a.id = :id")
    Optional<Asset> findByIdUsingFetchJoin(Long id);

}
