package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.domain.repository.PreferEstateRepository;
import com.example.Estate_Twin.estate.service.PreferEstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreferEstateServiceImpl implements PreferEstateService {
    private final HouseRepository houseRepository;
    private final EstateRepository estateRepository;
    private final PreferEstateRepository preferEstateRepository;

    @Override
    public PreferEstateResponseDto savePreferEstate(Long estateId, User user, Preference prefer) {
        Estate estate = estateRepository.findById(estateId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND));

        preferEstateRepository.save(PreferEstate.builder()
                .preference(prefer)
                .estate(estate)
                .user(user)
                .build());
        return new PreferEstateResponseDto(user, estate, houseRepository.findHouseByEstate_Id(estateId).orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND)));
    }

    @Override
    public Page<EstateListResponseDto> getPreferEstate(User user, Preference prefer, Pageable pageable) {
        return preferEstateRepository.findByUserIdAndPrefer(user.getId(), prefer, pageable);
    }
}
