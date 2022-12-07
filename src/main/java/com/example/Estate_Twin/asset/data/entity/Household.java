package com.example.Estate_Twin.asset.data.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Household {
    AIRCONDITIONER("AIRCONDITIONER"), WASHER("WASHER"), BED("BED"), DESK("DESK"),
    CLOSET("CLOSET"), TV("TV"), REFRIGERATOR("REFRIGERATOR"), SHOERACK("SHOERACK"),
    GASSTOVE("GASSTOVE"), DOORLOCK("DOORLOCK"), BIDET("BIDET"), WALLPAPER("WALLPAPER"),
    CURTAIN("CURTAIN"), INDUCTION("INDUCTION"), MICROWAVE("MICROWAVE");
    private final String type;

    Household(String type) {
        this.type = type;
    }

    public static Household of(String type) {
        return Arrays.stream(Household.values())
                .filter(v -> v.getType().equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("에셋 옵션 유형에 %s가 존재하지 않습니다.", type)));
    }
}
