package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.user.domain.dao.UserDAO;
import com.example.Estate_Twin.user.service.UserService;
import com.example.Estate_Twin.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    @Override
    public UserResponseDto getUserbyId(Long id) {
        return new UserResponseDto(userDAO.findUserById(id));
    }

    @Override
    public UserResponseDto getUserbyEmail(String email) {
        return new UserResponseDto(userDAO.findUserByEmail(email));
    }

    @Override
    public UserResponseDto signUp(String email, UserSignUpDto userSignUpDto) {
        return new UserResponseDto(userDAO.signUp(email, userSignUpDto.getBirthday(), userSignUpDto.getPhone(), EstateType.of(userSignUpDto.getEstateType()), TransactionType.of(userSignUpDto.getTransactionType())));
    }

}
