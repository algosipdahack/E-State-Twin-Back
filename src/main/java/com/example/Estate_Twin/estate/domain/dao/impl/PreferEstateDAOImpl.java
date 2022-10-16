package com.example.Estate_Twin.estate.domain.dao.impl;

import com.example.Estate_Twin.estate.domain.dao.PreferEstateDAO;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.repository.PreferEstateRepository;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
@Transactional(readOnly = true)
public class PreferEstateDAOImpl implements PreferEstateDAO {
    private PreferEstateRepository preferEstateRepository;
    @Transactional
    @Override
    public PreferEstate savePreferEstate(Estate estate, User user, Preference prefer) {
        return preferEstateRepository.save(PreferEstate.builder()
                .preference(prefer)
                .estate(estate)
                .user(user)
                .build());
    }

    @Override
    public boolean existPreferEstate(Long estateId, Long userId, Preference prefer) {
        return preferEstateRepository.existsByEstateIdAndUserIdAndPrefer(estateId, userId, prefer);
    }

    @Override
    public List<EstateListResponseDto> findPreferEstate(Long userId, Preference prefer, Pageable pageable) {
        return preferEstateRepository.findByUserIdAndPrefer(userId, prefer, pageable);
    }
}
