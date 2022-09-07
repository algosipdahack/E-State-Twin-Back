package com.example.Estate_Twin.user.domain.dao;

import com.example.Estate_Twin.user.domain.entity.User;

public interface UserDAO {
    User findUser(Long id);
}
