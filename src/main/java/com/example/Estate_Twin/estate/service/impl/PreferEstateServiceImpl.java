package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.estate.domain.dao.impl.*;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.service.PreferEstateService;
import com.example.Estate_Twin.estate.web.dto.PreferEstateResponseDto;
import com.example.Estate_Twin.user.domain.dao.impl.UserDAOImpl;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreferEstateServiceImpl implements PreferEstateService {
    private final PreferEstateDAOImpl preferEstateDAO;
    private final EstateDAOImpl estateDAO;
    private final UserDAOImpl userDAO;
    //매물 찜하기
    @Override
    public PreferEstateResponseDto preferEstate(Long estateId, String email, Preference prefer) {
        User user = userDAO.findUserByEmail(email);
        Estate estate = estateDAO.findEstate(estateId);
        return new PreferEstateResponseDto(preferEstateDAO.savePreferEstate(estate, user, prefer));
    }
}
