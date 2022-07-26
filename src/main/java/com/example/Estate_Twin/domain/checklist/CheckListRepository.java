package com.example.Estate_Twin.domain.checklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CheckListRepository extends JpaRepository<CheckList,Long> {
}
