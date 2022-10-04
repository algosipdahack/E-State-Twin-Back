package com.example.Estate_Twin.auth.service;

import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    //email 로 조회
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with email : " + usernameOrEmail));
        return CustomUserDetails.create(user);
    }
}
