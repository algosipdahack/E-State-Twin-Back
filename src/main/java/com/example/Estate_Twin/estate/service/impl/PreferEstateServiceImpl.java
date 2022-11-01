package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.estate.domain.dao.impl.*;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.service.PreferEstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreferEstateServiceImpl implements PreferEstateService {
    private final PreferEstateDAOImpl preferEstateDAO;
    private final EstateDAOImpl estateDAO;

    @Override
    public PreferEstateResponseDto savePreferEstate(Long estateId, User user, Preference prefer) {
        Estate estate = estateDAO.findEstate(estateId);
        return new PreferEstateResponseDto(preferEstateDAO.savePreferEstate(estate, user, prefer));
    }

    @Override
    public List<EstateListResponseDto> getPreferEstate(User user, Preference prefer, Pageable pageable) {
        Long userId = user.getId();
        return preferEstateDAO.findPreferEstate(userId, prefer, pageable);
    }
}
