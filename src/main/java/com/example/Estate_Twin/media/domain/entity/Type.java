package com.example.Estate_Twin.media.domain.entity;

import lombok.Getter;

import java.util.Arrays;
@Getter
public enum Type {
    VIDEO("video"),PHOTO("photo");
    private final String type;
    Type(String type) {
        this.type = type;
    }
    public static Type of(String type) {
        return Arrays.stream(Type.values())
                .filter(v -> v.getType().equals(type))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(String.format("미디어 유형에 에 %s 가 존재하지 않습니다.",type)));
    }
}
