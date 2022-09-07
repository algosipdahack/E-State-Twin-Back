package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.DipEstate;
import com.example.Estate_Twin.user.web.dto.UserDto;
import lombok.Getter;

@Getter
public class DipEstateResponseDto {
    private UserDto user;
    private EstateDto estate;

    public DipEstateResponseDto(DipEstate dipEstate) {
        this.user = new UserDto(dipEstate.getUser());
        this.estate = new EstateDto(dipEstate.getEstate());
    }
}
