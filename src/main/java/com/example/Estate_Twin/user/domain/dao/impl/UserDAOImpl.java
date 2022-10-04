package com.example.Estate_Twin.user.domain.dao.impl;

import com.example.Estate_Twin.user.domain.dao.UserDAO;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.domain.repository.UserRepository;
import com.example.Estate_Twin.user.web.dto.UserSignUpDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class UserDAOImpl implements UserDAO {
    private UserRepository userRepository;

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id = "+id));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email = "+email));
    }

    @Override
    @Transactional
    public User signUp(String email, UserSignUpDto dto) {
        return findUserByEmail(email).signup(dto);
    }

}
