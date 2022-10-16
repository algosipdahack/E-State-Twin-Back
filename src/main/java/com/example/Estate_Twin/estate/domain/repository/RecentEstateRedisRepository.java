package com.example.Estate_Twin.estate.domain.repository;

import com.example.Estate_Twin.estate.domain.entity.RecentEstate;
import org.springframework.data.repository.CrudRepository;

public interface RecentEstateRedisRepository extends CrudRepository<RecentEstate, Long> {
}
