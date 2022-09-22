package com.example.Estate_Twin.user.web.dto;

import com.example.Estate_Twin.user.domain.entity.Role;
import lombok.*;

@Getter
@NoArgsConstructor
public class LoginResponseDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private String tokenType;
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponseDto(Long id, String name, String email, Role role, String tokenType, String accessToken, String refreshToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
