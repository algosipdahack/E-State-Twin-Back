package com.example.Estate_Twin.auth.service;

import com.example.Estate_Twin.auth.jwt.JwtTokenProvider;
import com.example.Estate_Twin.user.domain.entity.AuthProvider;
import com.example.Estate_Twin.user.domain.entity.Role;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.verify;

public class AuthServiceTest {
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private CustomUserDetailService customUserDetailService = new CustomUserDetailService(userRepository);
    private JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(customUserDetailService);
    User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("daowll@naver.com")
                .authProvider(AuthProvider.KAKAO)
                .role(Role.USER)
                .name("이게된다고")
                .build();
    }
    /*@Test
    void createToken(){
        Mockito.when(userRepository.findById(4L))
                .thenReturn(Optional.of(user));

        String refreshToken = jwtTokenProvider.createRefreshToken(user);
        System.out.println(refreshToken);
        verify(userRepository).findById(4L); // 해당 함수가 호출되었는지를 검증

    }
*/
}