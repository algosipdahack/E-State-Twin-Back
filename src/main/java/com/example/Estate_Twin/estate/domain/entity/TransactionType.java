package com.example.Estate_Twin.estate.domain.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TransactionType {
    //월세, 전세, 매매
    MONTHLYRENT("MONTHLYRENT"), LEASE("LEASE"), TRADING("TRADING");
    private final String type;
    TransactionType(String type) {
        this.type = type;
    }
    public static TransactionType of(String type) {
        return Arrays.stream(TransactionType.values())
                .filter(v -> v.getType().equals(type))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(String.format("매물 거래 유형에 %s 가 존재하지 않습니다.",type)));
    }
}
