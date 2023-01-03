package com.example.Estate_Twin.auth.service;

import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserDAOImpl userDAO;

    @Override
    @Transactional
    //email 로 조회
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userDAO.findUserByEmail(usernameOrEmail);
        return CustomUserDetails.create(user);
    }
}
