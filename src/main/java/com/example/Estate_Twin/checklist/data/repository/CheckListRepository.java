package com.example.Estate_Twin.checklist.data.repository;

import com.example.Estate_Twin.checklist.data.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckListRepository extends JpaRepository<CheckList,Long> {
    List<CheckList> findAllByOrderByIdDesc();
}
