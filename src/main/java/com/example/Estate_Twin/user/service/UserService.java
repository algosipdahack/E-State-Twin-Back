package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.user.web.dto.*;


public interface UserService {
    UserResponseDto getUserbyId(Long id);
    UserResponseDto getUserbyEmail(String email);
    UserResponseDto signUp(String email, UserSignUpDto userSignUpDto);
}
