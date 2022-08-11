package com.example.Estate_Twin.estate.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Rank {
    RANK_MANY("rank_many"), RANK_POPULAR("rank_popular");
    //여기 안에서 아예 배정을 해줘도 좋을 듯
    private final String rank;
    Rank(String rank) { this.rank = rank;}

    public static Rank of(String rank) {
        return Arrays.stream(Rank.values())
                .filter(v->v.getRank().equals(rank))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException(String.format("매물 뱃지 유형에 %s가 존재하지 않습니다.",rank)));
    }
}
