package com.example.Estate_Twin.user.domain.entity;

import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
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
                .orElseThrow(()->new CheckHouseException(ErrorCode.ROLE_NOT_FOUND));
    }
}
