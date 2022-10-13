package com.example.Estate_Twin.user.domain.dao;

import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.web.dto.UserSignUpDto;

public interface UserDAO {
    User findUserById(Long id);
    User findUserByEmail(String email);
    User signUp(String email, UserSignUpDto dto);
    User deleteMember(String email);
}
