package com.example.Estate_Twin.user.domain.dao.impl;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.user.domain.dao.UserDAO;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
    public User signUp(String email, LocalDate birthday, String phone, EstateType estateType, TransactionType transactionType) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email = "+email))
                .builder()
                .birthday(birthday)
                .phone(phone)
                .estateType(estateType)
                .transactionType(transactionType)
                .build();
        return userRepository.save(user);
    }
}
