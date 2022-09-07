package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.user.web.dto.UserResponseDto;

public interface UserService {
    UserResponseDto getUser(Long id);
}
