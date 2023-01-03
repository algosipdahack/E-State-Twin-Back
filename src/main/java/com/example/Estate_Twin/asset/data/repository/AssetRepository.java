package com.example.Estate_Twin.asset.data.repository;

import com.example.Estate_Twin.asset.data.entity.Asset;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface AssetRepository extends JpaRepository<Asset,Long>, AssetRepositoryCustom {
    @Query("select a from Asset a join fetch a.checkLists as checklists where a.id = :id order by checklists.repairDate desc")
    Optional<Asset> findByIdUsingFetchJoinCheckList(@Param("id") Long id);
    Optional<List<Asset>> findAssetsByEstate_Id(Long estateId);
}
