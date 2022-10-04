package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.PreferEstate;
import com.example.Estate_Twin.user.web.dto.UserInfoDto;
import lombok.Getter;

@Getter
public class PreferEstateResponseDto {
    private Long id;
    private UserInfoDto user;
    private EstateListResponseDto estate;

    public PreferEstateResponseDto(PreferEstate preferEstate) {
        this.id = preferEstate.getId();
        this.user = new UserInfoDto(preferEstate.getUser());
        this.estate = new EstateListResponseDto(preferEstate.getEstate());
    }
}
