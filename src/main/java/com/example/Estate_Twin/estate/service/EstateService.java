package com.example.Estate_Twin.estate.service;

import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.user.domain.entity.Broker;
import com.example.Estate_Twin.user.domain.entity.User;

import java.util.List;

public interface EstateService {
    EstateResponseDto getEstate(Long id);
    EstateResponseDto saveEstate(EstateSaveRequestDto estateSaveRequestDto);
    Long saveFirst(AddressSaveRequestDto addressSaveRequestDto, Long brokerId, String email);
    EstateResponseDto updateEstate(Long id, EstateUpdateRequestDto estateUpdateRequestDto);
    Estate addMedia(Long id, Media media);
    void clearMedia(Long id);
    List<EstateListResponseDto> getAllEstate();
    List<EstateMainDto> getEstateCustomized(String borough);
    EstateResponseDto allowPost(Long estateId, String userEmail);
    Estate checkRoleBroker(Long estateId, Broker broker);
    Estate checkRoleOwner(Long estateId, User owner);
    boolean checkEnroll(Estate estate);
}
