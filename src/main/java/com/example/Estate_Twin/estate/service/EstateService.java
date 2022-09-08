package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.media.domain.entity.Media;

import java.util.List;

public interface EstateService {
    EstateResponseDto getEstate(Long id);
    EstateDto saveEstate(EstateSaveRequestDto estateSaveRequestDto, Long houseId);
    EstateResponseDto updateEstate(Long id, EstateUpdateRequestDto estateUpdateRequestDto);
    Estate addMedia(Long id, Media media);
    void clearMedia(Long id);
    List<EstateListResponseDto> getAllEstate();
    List<EstateListResponseDto> getEstateCustomized(String borough);
    EstateResponseDto allowPost(Long estateId, Long userId);
}
