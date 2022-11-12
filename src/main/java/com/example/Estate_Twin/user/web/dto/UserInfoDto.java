package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.user.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
@Getter
public class UserInfoDto {
    private final Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
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

    @Builder
    public UserInfoDto(User user) {
        this.id = user.getId();
        this.birthday = user.getBirthday();
        this.phone = user.getPhone();
        this.name = user.getName();
        this.email = user.getEmail();
        this.borough = user.getBorough();
        this.isBroker = user.isBroker();
        this.authProvider = user.getAuthProvider().toString();
        this.estateType = user.getEstateType().toString();
        this.transactionType = user.getTransactionType().toString();
        this.role = user.getRole().toString();
    }
}
