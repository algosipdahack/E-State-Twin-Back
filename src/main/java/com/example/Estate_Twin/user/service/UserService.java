package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.user.web.dto.*;


public interface UserService {
    UserResponseDto getUserbyId(Long id);
    UserResponseDto getUserbyEmail(String email);
    UserInfoDto signUp(String email, UserSignUpDto userSignUpDto);
}
