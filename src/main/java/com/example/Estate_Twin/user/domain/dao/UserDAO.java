package com.example.Estate_Twin.user.domain.dao;

import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.user.domain.entity.User;

import java.time.LocalDate;

public interface UserDAO {
    User findUserById(Long id);
    User findUserByEmail(String email);
    User signUp(String email, LocalDate birthday, String phone, EstateType estateType, TransactionType transactionType);
}
