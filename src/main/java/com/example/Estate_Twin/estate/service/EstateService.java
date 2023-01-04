package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.address.*;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.user.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EstateService {
    EstateDetailDto getEstate(Long id, User user);
    EstateResponseDto saveEstate(EstateSaveRequestDto estateSaveRequestDto);
    Long saveFirst(Address address, Long brokerId, Long userId);
    EstateResponseDto updateEstate(Long id, EstateUpdateRequestDto estateUpdateRequestDto);
    Page<EstateListResponseDto> getAllEstate(Long estateId, Pageable pageable);
    List<EstateMainDto> getEstateCustomized(User user);
    EstateResponseDto allowPost(Long estateId, User user);
    ContractStateResponseDto startContract(Long estateId, User user);
    List<EstateListResponseDto> searchEstate(User user, String borough, String town, Pageable pageable);
}
