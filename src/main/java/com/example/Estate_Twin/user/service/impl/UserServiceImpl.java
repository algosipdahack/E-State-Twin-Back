package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.user.domain.dao.impl.UserDAOImpl;
import com.example.Estate_Twin.user.service.UserService;
import com.example.Estate_Twin.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAOImpl userDAO;
    @Override
    public UserResponseDto getUserbyId(Long id) {
        return new UserResponseDto(userDAO.findUserById(id));
    }

    @Override
    public UserResponseDto getUserbyEmail(String email) {
        return new UserResponseDto(userDAO.findUserByEmail(email));
    }

    @Override
    public UserInfoDto signUp(String email, UserSignUpDto userSignUpDto) {
        return new UserInfoDto(userDAO.signUp(email, userSignUpDto));
    }

}
