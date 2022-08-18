package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import com.example.Estate_Twin.estate.web.dto.EstateSaveRequestDto;
import com.example.Estate_Twin.estate.web.dto.EstateUpdateRequestDto;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface EstateService {
    EstateResponseDto getEstate(Long id);
    EstateResponseDto saveEstate(EstateSaveRequestDto estateSaveRequestDto, Long houseId);
    EstateResponseDto updateEstate(Long id, EstateUpdateRequestDto estateUpdateRequestDto);

}
