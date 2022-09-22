package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.user.web.dto.*;

import javax.validation.constraints.Null;

public interface UserService {
    UserResponseDto getUser(Long id);
    UserResponseDto signUp(Long userId, UserSignUpDto userSignUpDto);
}
