package com.example.Estate_Twin.checklist.data.repository;

import com.example.Estate_Twin.checklist.data.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.*;

public interface CheckListRepository extends JpaRepository<CheckList,Long> , CheckListRepositoryCustom{
    Optional<List<CheckList>> findCheckListsByAsset_IdOrderByRepairDateDesc(Long assetId);
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT) // 버전을 강제로 증가시킴
    @Query(value = "select c from CheckList c where c.id = :id")
    Optional<CheckList> findByIdForUpdate(@Param("id") Long id);
}
