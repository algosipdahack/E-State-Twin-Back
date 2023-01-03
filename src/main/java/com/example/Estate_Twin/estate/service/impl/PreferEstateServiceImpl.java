package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.estate.domain.dao.impl.*;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.service.PreferEstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreferEstateServiceImpl implements PreferEstateService {
    private final PreferEstateDAOImpl preferEstateDAO;
    private final EstateDAOImpl estateDAO;
    private final HouseDAOImpl houseDAO;
    private final HouseRepository houseRepository;

    @Override
    public PreferEstateResponseDto savePreferEstate(Long estateId, User user, Preference prefer) {
        Estate estate = estateDAO.findEstate(estateId);
        preferEstateDAO.savePreferEstate(estate, user, prefer);
        return new PreferEstateResponseDto(user, estate, houseRepository.findHouseByEstate_Id(estateId).orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND)));
    }

    @Override
    public Page<EstateListResponseDto> getPreferEstate(User user, Preference prefer, Pageable pageable) {
        return preferEstateDAO.findPreferEstate(user.getId(), prefer, pageable);
    }
}
