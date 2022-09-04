package com.example.Estate_Twin.user.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
@Getter
@RequiredArgsConstructor
public enum Role {
    OWNER("ROLE_OWNER","집주인"), TANENT("ROLE_TANENT","세입자");
    private final String key;
    private final String title;

    public static Role of(String role) {
        return Arrays.stream(Role.values())
                .filter(v->v.getKey().equals(role))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException(String.format("사용자 유형에 %s가 존재하지 않습니다.",role)));
    }
}
