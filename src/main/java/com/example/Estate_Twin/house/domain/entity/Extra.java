package com.example.Estate_Twin.house.domain.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Extra {
    VERANDA("VERANDA"), BALCONY("BALCONY");
    private final String extra;
    Extra(String extra) { this.extra = extra; }

    public static Extra of(String extra) {
        return Arrays.stream(Extra.values())
                .filter(v->v.getExtra().equals(extra))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException(String.format("베란다/발코니에 %s가 존재하지 않습니다.",extra)));
    }
}