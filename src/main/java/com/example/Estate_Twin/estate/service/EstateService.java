package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.address.AddressSearchDto;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.estate.web.dto.*;

import java.util.List;

public interface EstateService {
    EstateDetailDto getEstate(Long id, String email);
    EstateResponseDto saveEstate(EstateSaveRequestDto estateSaveRequestDto);
    Long saveFirst(Address address, Long brokerId, String email);
    EstateResponseDto updateEstate(Long id, EstateUpdateRequestDto estateUpdateRequestDto);
    List<EstateListResponseDto> getAllEstate();
    List<EstateMainDto> getEstateCustomized(String borough);
    EstateResponseDto allowPost(Long estateId, String userEmail);
    ContractStateResponseDto startContract(Long estateId, String email);
    List<EstateListResponseDto> searchEstate(AddressSearchDto addressSearchDto);
}
