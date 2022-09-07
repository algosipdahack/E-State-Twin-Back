package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.DipEstate;
import com.example.Estate_Twin.user.web.dto.UserDto;
import lombok.Getter;


@Getter
public class DipEstateDto {
    private Long id;
    private UserDto user;
    private EstateDto estate;

    public DipEstateDto(DipEstate dipEstate) {
        this.id = dipEstate.getId();
        this.user = new UserDto(dipEstate.getUser());
        this.estate = new EstateDto(dipEstate.getEstate());
    }
}
