package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserSignUpDto {
    private LocalDate birthday;
    private String phone;
    private AddressDto address;
    private EstateType estateType;
    private TransactionType transactionType;

    @Builder
    public UserSignUpDto(LocalDate birthday, String phone, AddressDto address, EstateType estateType, TransactionType transactionType) {
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.estateType = estateType;
        this.transactionType = transactionType;
    }

    public User toEntity() {
        return User.builder()
                .birthday(birthday)
                .phone(phone)
                .estateType(estateType)
                .transactionType(transactionType)
                .build();
    }
}
