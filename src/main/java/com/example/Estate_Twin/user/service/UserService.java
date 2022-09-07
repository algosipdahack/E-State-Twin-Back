package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.user.web.dto.UserResponseDto;
import com.example.Estate_Twin.user.web.dto.UserSignUpDto;

public interface UserService {
    UserResponseDto getUser(Long id);
    UserResponseDto signUp(Long id, UserSignUpDto userSignUpDto);
}
