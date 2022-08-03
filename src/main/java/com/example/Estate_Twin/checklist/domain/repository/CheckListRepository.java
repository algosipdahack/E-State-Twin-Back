package com.example.Estate_Twin.checklist.domain.repository;

import com.example.Estate_Twin.checklist.domain.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckListRepository extends JpaRepository<CheckList,Long> {
}
