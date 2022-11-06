package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.user.domain.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

@Getter
public class UserDto {
    private final LocalDate birthday;
    private final String phone;
    private final String name;
    private final String email;
    private final boolean isBroker;
    @Schema(description = "선호 지역", example = "강남구")
    private String borough;
    @Schema(description = "OAuth provider", example = "KAKAO, NAVER, GOOGLE")
    private final String authProvider;
    @Schema(description = "매물 거래 유형", example = "MONTHLYRENT, LEASE, TRADING")
    private final String transactionType;
    @Schema(description = "매물 거래 종류", example = "APARTMENT, OFFICETELS")
    private final String estateType;
    @Schema(description = "유저 지위", example = "USER")
    private final String role;
    private final EstateDto tenantEstate;
    private final Set<EstateDto> ownEstate;
    private final Set<PreferEstateDto> dipEstates;

    public UserDto(User user) {
        this.birthday = user.getBirthday();
        this.phone = user.getPhone();
        this.name = user.getName();
        this.email = user.getEmail();
        this.isBroker = user.isBroker();
        this.borough = user.getBorough();
        this.authProvider = user.getAuthProvider().toString();
        this.estateType = user.getEstateType().toString();
        this.transactionType = user.getTransactionType().toString();
        this.role = user.getRole().toString();
        this.tenantEstate = new EstateDto(user.getTenantEstate());
        this.ownEstate = new HashSet<>();
        user.getOwnEstates().forEach(ownEstate -> this.ownEstate.add(new EstateDto(ownEstate)));
        this.dipEstates = new HashSet<>();
        user.getPreferEstates().forEach(dipEstate -> this.dipEstates.add(new PreferEstateDto(dipEstate)));
    }
}