package com.example.Estate_Twin.user.domain.entity;

import lombok.*;

import java.util.Arrays;
@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER","회원"), ADMIN("ROLE_ADMIN","관리자");
    private final String key;
    private final String title;

    public static Role of(String role) {
        return Arrays.stream(Role.values())
                .filter(v->v.getKey().equals(role))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException(String.format("사용자 유형에 %s가 존재하지 않습니다.",role)));
    }
}
