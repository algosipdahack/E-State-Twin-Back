package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.user.domain.entity.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

@Getter
public class UserResponseDto {
    private final Long id;
    private final LocalDate birthday;
    private final String phone;
    private final String name;
    private final String email;
    private final AuthProvider authProvider;
    private final AddressDto address;
    private final EstateType estateType;
    private final TransactionType transactionType;
    private final Role role;
    private final EstateDto tanentEstate;
    private final List<EstateDto> ownEstate;
    private final Set<DipEstateDto> dipEstates;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.birthday = user.getBirthday();
        this.phone = user.getPhone();
        this.name = user.getName();
        this.email = user.getEmail();
        this.authProvider = user.getAuthProvider();
        this.address = new AddressDto(user.getAddress());
        this.estateType = user.getEstateType();
        this.transactionType = user.getTransactionType();
        this.role = user.getRole();
        this.tanentEstate = new EstateDto(user.getTanentEstate());
        this.ownEstate = new ArrayList<>();
        user.getOwnEstates().forEach(ownEstate -> this.ownEstate.add(new EstateDto(ownEstate)));
        this.dipEstates = new HashSet<>();
        user.getDipEstates().forEach(dipEstate -> this.dipEstates.add(new DipEstateDto(dipEstate)));
    }
}
