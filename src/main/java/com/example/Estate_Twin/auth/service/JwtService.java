package com.example.Estate_Twin.auth.service;

import com.example.Estate_Twin.auth.jwt.*;
import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;
    @Transactional
    public String refreshToken(String oldRefreshToken, String oldAccessToken) {

        if(!tokenProvider.validateToken(oldRefreshToken)) {
            throw new RuntimeException("Refresh Token이 만료되었습니다. 다시 로그인해주세요");
        }

        // 유저정보 얻기
        Authentication authentication = tokenProvider.getAuthentication(oldAccessToken);
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        String email = user.getUsername();

        // Match Refresh Token
        String savedToken = userRepository.getRefreshTokenByEmail(email);
        User r_user = userRepository.findByEmail(user.getEmail())
                .orElseThrow(()->new IllegalArgumentException("해당 유저를 찾을 수 없습니다. email = "+user.getEmail()));
        if(!savedToken.equals(oldRefreshToken)) {
            throw new RuntimeException("Not Matched Refresh Token");
        }

        //JWT 갱신
        String accessToken = tokenProvider.createAccessToken(r_user);
        return accessToken;
    }

    public String refreshToken(String oldAccessToken) {

        // 유저정보 얻기

        User r_user = userRepository.findByEmail("daowll@naver.com")
                .orElseThrow(()->new IllegalArgumentException("해당 유저를 찾을 수 없습니다. email = "));

        // Match Refresh Token

        //JWT 갱신
        String accessToken = tokenProvider.createAccessToken(r_user);
        String refreshToken = tokenProvider.createRefreshToken(r_user);
        return accessToken;
    }

}
