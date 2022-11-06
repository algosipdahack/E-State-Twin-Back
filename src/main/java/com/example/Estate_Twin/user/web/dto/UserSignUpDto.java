package com.example.Estate_Twin.user.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserSignUpDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birthday;
    private String phone;
    @Schema(description = "매물 거래 유형", example = "MONTHLYRENT, LEASE, TRADING")
    private String transactionType;
    @Schema(description = "매물 거래 종류", example = "APARTMENT, OFFICETELS")
    private String estateType;
    @Schema(description = "선호 지역", example = "강남구")
    private String borough;
    @Builder
    public UserSignUpDto(LocalDate birthday, String phone, String transactionType, String estateType, String borough) {
        this.birthday = birthday;
        this.phone = phone;
        this.transactionType = transactionType;
        this.estateType = estateType;
        this.borough = borough;
    }
}
