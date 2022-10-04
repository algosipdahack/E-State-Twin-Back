package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.PreferEstate;
import com.example.Estate_Twin.user.web.dto.UserDto;
import com.example.Estate_Twin.user.web.dto.UserInfoDto;
import lombok.Getter;

@Getter
public class PreferEstateDto {
    private UserInfoDto user;
    private EstateListResponseDto estate;

    public PreferEstateDto(PreferEstate preferEstate) {
        this.user = new UserInfoDto(preferEstate.getUser());
        this.estate = new EstateListResponseDto(preferEstate.getEstate());
    }
}
