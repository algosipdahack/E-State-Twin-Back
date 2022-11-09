package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.estate.domain.dao.impl.*;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.service.PreferEstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.domain.dao.impl.HouseDAOImpl;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreferEstateServiceImpl implements PreferEstateService {
    private final PreferEstateDAOImpl preferEstateDAO;
    private final EstateDAOImpl estateDAO;
    private final HouseDAOImpl houseDAO;

    @Override
    public PreferEstateResponseDto savePreferEstate(Long estateId, User user, Preference prefer) {
        Estate estate = estateDAO.findEstate(estateId);
        return new PreferEstateResponseDto(preferEstateDAO.savePreferEstate(estate, user, prefer), estate, houseDAO.findHouseByEstateId(estateId));
    }

    @Override
    public Page<EstateListResponseDto> getPreferEstate(User user, Preference prefer, Pageable pageable) {
        return preferEstateDAO.findPreferEstate(user.getId(), prefer, pageable);
    }
}
