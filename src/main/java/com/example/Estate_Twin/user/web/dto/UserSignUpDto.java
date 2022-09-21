package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.user.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserSignUpDto {
    private LocalDate birthday;
    private String phone;
    @Schema(description = "매물 거래 유형", example = "MONTHLYRENT, LEASE, TRADING")
    private String transactionType;
    @Schema(description = "매물 거래 종류", example = "APARTMENT, OFFICETELS")
    private String estateType;
    @Schema(description = "선호 지역", example = "강남구")
    private String borough;

    public User toEntity() {
        return User.builder()
                .birthday(birthday)
                .phone(phone)
                .borough(borough)
                .estateType(EstateType.of(estateType))
                .transactionType(TransactionType.of(transactionType))
                .build();
    }
}
