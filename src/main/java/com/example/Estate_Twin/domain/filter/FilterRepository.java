package com.example.Estate_Twin.domain.filter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FilterRepository extends JpaRepository<Filter,Long> {
}
