package com.example.Estate_Twin.estate.domain.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Grade {
    RANK_MANY("RANK_MANY"), RANK_POPULAR("RANK_POPULAR");
    //여기 안에서 아예 배정을 해줘도 좋을 듯
    private final String rank;
    Grade(String rank) { this.rank = rank;}

    public static Grade of(String rank) {
        return Arrays.stream(Grade.values())
                .filter(v->v.getRank().equals(rank))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException(String.format("매물 뱃지 유형에 %s가 존재하지 않습니다.",rank)));
    }
}
