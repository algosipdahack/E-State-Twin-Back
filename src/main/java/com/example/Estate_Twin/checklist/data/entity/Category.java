package com.example.Estate_Twin.checklist.data.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Category {

    HOMEAPPLIANCES("homeappliances"), FURNITURE("furniture"), BATHROOM("bathroom"), INTERIOR("interior");
    private final String type;

    Category(String type) {
        this.type = type;
    }

    public static Category of(String type) {
        return Arrays.stream(Category.values())
                .filter(v -> v.getType().equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("체크리스트 카테고리 유형에 %s 가 존재하지 않습니다.", type)));
    }
}
