package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.user.web.dto.*;

public interface UserService {
    UserResponseDto getUser(Long id);
    UserResponseDto signUp(Long userId, UserSignUpDto userSignUpDto);
}
