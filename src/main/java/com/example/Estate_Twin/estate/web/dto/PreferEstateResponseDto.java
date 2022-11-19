package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.user.web.dto.UserInfoDto;
import lombok.Getter;

@Getter
public class PreferEstateResponseDto {
    private Long estateId;
    private UserInfoDto user;
    private EstateListResponseDto estate;

    public PreferEstateResponseDto(PreferEstate preferEstate, Estate estate, House house) {
        this.estateId = preferEstate.getId();
        this.user = new UserInfoDto(preferEstate.getUser());
        this.estate = new EstateListResponseDto(estate, house);
    }
}
