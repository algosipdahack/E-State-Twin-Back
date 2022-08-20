package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.domain.dao.HouseDAO;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstateServiceImpl implements EstateService {
    private final EstateDAO estateDAO;
    private final HouseDAO houseDAO;

    @Override
    public EstateResponseDto getEstate(Long id) {
        return new EstateResponseDto(estateDAO.findEstate(id));
    }

    @Override
    public EstateResponseDto saveEstate(EstateSaveRequestDto estateSaveRequestDto, Long houseId) {
        estateSaveRequestDto.setHouse(houseDAO.findHouse(houseId));
        return new EstateResponseDto(estateDAO.saveEstate(estateSaveRequestDto.toEntity()));
    }

    @Override
    public Estate addMedia(Long id, List<Media> mediaList) {
        return estateDAO.addEstateMedia(id, mediaList);
    }

    @Override
    public EstateResponseDto updateEstate(Long id, EstateUpdateRequestDto estateUpdateRequestDto) {
        return new EstateResponseDto(estateDAO.updateEstate(id, estateUpdateRequestDto.getContent(), estateUpdateRequestDto.getModel(),
                estateUpdateRequestDto.getArCam(), estateUpdateRequestDto.getContractState(),
                estateUpdateRequestDto.getTransactionType(), estateUpdateRequestDto.getEstateThumbNail(),
                estateUpdateRequestDto.getCity(), estateUpdateRequestDto.getBorough(),
                estateUpdateRequestDto.getThumbNail3D()));
    }
}
