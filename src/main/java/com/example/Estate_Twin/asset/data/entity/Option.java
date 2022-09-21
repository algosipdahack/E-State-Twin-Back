package com.example.Estate_Twin.asset.data.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Option {
    AIRCONDITIONER("AIRCONDITIONER"), WASHER("WASHER"), BED("BED"), DESK("DESK"),
    CLOSET("CLOSET"), TV("TV"), REFRIGERATOR("REFRIGERATOR"), SHOERACK("SHOERACK"),
    GASSTOVE("GASSTOVE"), DOORLOCK("DOORLOCK"), BIDET("BIDET"), WALLPAPER("WALLPAPER"), CURTAIN("CURTAIN");
    private final String type;

    Option(String type) {
        this.type = type;
    }

    public static Option of(String type) {
        return Arrays.stream(Option.values())
                .filter(v -> v.getType().equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("에셋 옵션 유형에 %s가 존재하지 않습니다.", type)));
    }
}
