package com.example.Estate_Twin.user.domain.dao.impl;

import com.example.Estate_Twin.user.domain.dao.UserDAO;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDAOImpl implements UserDAO {
    private UserRepository userRepository;

    @Override
    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id = "+id));
    }
}
