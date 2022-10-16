package com.example.Estate_Twin.estate.domain.dao.impl;

import com.example.Estate_Twin.estate.domain.dao.RecentEstateDAO;
import com.example.Estate_Twin.estate.domain.entity.RecentEstate;
import com.example.Estate_Twin.estate.domain.repository.RecentEstateRedisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecentEstateDAOImpl implements RecentEstateDAO {
    private RecentEstateRedisRepository recentEstateRedisRepository;

    @Override
    public void saveRecentEstate(RecentEstate recentEstate) {
        recentEstateRedisRepository.save(recentEstate);
    }
}
