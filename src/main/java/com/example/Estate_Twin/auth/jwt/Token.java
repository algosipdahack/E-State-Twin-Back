package com.example.Estate_Twin.auth.jwt;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor
public class Token {
    private String accessToken;
    private String refreshToken;
    private Boolean isMember;

    public Token(String accessToken, String refreshToken, Boolean isMember) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.isMember = isMember;
    }
}
