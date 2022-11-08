package com.example.Estate_Twin.checklist.data.repository;

import com.example.Estate_Twin.checklist.data.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface CheckListRepository extends JpaRepository<CheckList,Long> , CheckListRepositoryCustom{
    Optional<List<CheckList>> findCheckListsByAsset_Id(Long assetId);
}
