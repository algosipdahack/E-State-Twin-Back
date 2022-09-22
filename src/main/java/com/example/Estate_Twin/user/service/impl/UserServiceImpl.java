package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.user.domain.dao.UserDAO;
import com.example.Estate_Twin.user.domain.entity.AuthProvider;
import com.example.Estate_Twin.user.domain.entity.Role;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.service.UserService;
import com.example.Estate_Twin.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    @Override
    public UserResponseDto getUser(Long id) {
        return new UserResponseDto(userDAO.findUser(id));
    }

    @Override
    public UserResponseDto signUp(Long userId, UserSignUpDto userSignUpDto) {
        return new UserResponseDto(userDAO.signUp(userId, userSignUpDto.getBirthday(), userSignUpDto.getPhone(), EstateType.of(userSignUpDto.getEstateType()), TransactionType.of(userSignUpDto.getTransactionType())));
    }

}
