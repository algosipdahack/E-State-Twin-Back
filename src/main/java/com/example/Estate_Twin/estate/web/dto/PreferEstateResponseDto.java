package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.web.dto.UserInfoDto;
import lombok.*;

@Getter
public class PreferEstateResponseDto {
    private Long estateId;
    private UserInfoDto user;
    private EstateListResponseDto estate;
    @Builder
    public PreferEstateResponseDto(User user, Estate estate, House house) {
        this.estateId = estate.getId();
        this.user = new UserInfoDto(user);
        this.estate = new EstateListResponseDto(estate, house);
    }
}
