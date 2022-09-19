package com.example.Estate_Twin.estate.domain.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EstateType {
    APARTMENT("APARTMENT"), OFFICETELS("OFFICETELS");
    private final String type;

    EstateType(String type) {
        this.type = type;
    }
    public static EstateType of(String type) {
        return Arrays.stream(EstateType.values())
                .filter(v -> v.getType().equals(type))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(String.format("매물 유형에 %s 가 존재하지 않습니다.",type)));
    }
}
