package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.estate.web.dto.*;

import java.util.List;

public interface EstateService {
    EstateResponseDto getEstate(Long id);
    EstateResponseDto saveEstate(EstateSaveRequestDto estateSaveRequestDto);
    Long saveFirst(Address address, Long brokerId, String email);
    EstateResponseDto updateEstate(Long id, EstateUpdateRequestDto estateUpdateRequestDto);
    List<EstateListResponseDto> getAllEstate();
    List<EstateMainDto> getEstateCustomized(String borough);
    EstateResponseDto allowPost(Long estateId, String userEmail);
}
