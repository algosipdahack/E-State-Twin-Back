package com.example.Estate_Twin.user.domain.dao;

import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.user.domain.entity.User;

import java.time.LocalDate;

public interface UserDAO {
    User findUser(Long id);
    User signUp(Long id, LocalDate birthday, String phone, EstateType estateType);
}
