package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import com.example.Estate_Twin.estate.web.dto.EstateSaveRequestDto;
import com.example.Estate_Twin.estate.web.dto.EstateUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstateServiceImpl implements EstateService {
    private final EstateDAO estateDAO;

    @Override
    public EstateResponseDto getEstate(Long id) {
        return new EstateResponseDto(estateDAO.findEstate(id));
    }

    @Override
    public EstateResponseDto saveEstate(EstateSaveRequestDto estateSaveRequestDto, Long houseId) {
        return null;
    }

    @Override
    public EstateResponseDto updateEstate(Long id, EstateUpdateRequestDto estateUpdateRequestDto) {
        return null;
    }
}
