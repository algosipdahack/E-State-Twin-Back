package com.example.Estate_Twin.auth.jwt;

import lombok.*;
import org.springframework.stereotype.Service;

@ToString
@Getter
@NoArgsConstructor
public class Token {
    private String accessToken;
    private String refreshToken;

    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
