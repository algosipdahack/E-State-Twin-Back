package com.example.Estate_Twin.house.domain.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Structure {
    LOFT("LOFT"), SEPARATE_KITCHEN("SEPARATE_KITCHEN");
    private final String structure;
    Structure(String structure) { this.structure = structure; }

    public static Structure of(String structure) {
        return Arrays.stream(Structure.values())
                .filter(v->v.getStructure().equals(structure))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException(String.format("방 구조에 %s가 존재하지 않습니다.",structure)));
    }
}
