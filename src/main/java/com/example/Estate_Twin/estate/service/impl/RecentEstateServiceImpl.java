package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.estate.domain.dao.impl.RecentEstateDAOImpl;
import com.example.Estate_Twin.estate.domain.entity.RecentEstate;
import com.example.Estate_Twin.estate.service.RecentEstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RecentEstateServiceImpl implements RecentEstateService {
    private final RecentEstateDAOImpl recentEstateDAO;

    @Override
    public void saveRecentEstate(Long userId, Long estateId) {
        recentEstateDAO.saveRecentEstate(new RecentEstate(userId, estateId, LocalDateTime.now()));
    }
}
