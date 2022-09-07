package com.example.Estate_Twin.auth.service;

import com.example.Estate_Twin.auth.jwt.*;
import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import com.example.Estate_Twin.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    public String refreshToken(String oldRefreshToken, String oldAccessToken) {

        if(!tokenProvider.validateToken(oldRefreshToken)) {
            throw new RuntimeException("Refresh Token이 만료되었습니다. 다시 로그인해주세요");
        }

        // 유저정보 얻기
        Authentication authentication = tokenProvider.getAuthentication(oldAccessToken);
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        Long id = Long.valueOf(user.getName());

        // Match Refresh Token
        String savedToken = userRepository.getRefreshTokenById(id);

        if(!savedToken.equals(oldRefreshToken)) {
            throw new RuntimeException("Not Matched Refresh Token");
        }

        //JWT 갱신
        Token token = tokenProvider.createToken(authentication);
        return token.getAccessToken();
    }

}
