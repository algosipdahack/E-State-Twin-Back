package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
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
                .orElseThrow(()->new CheckHouseException(ErrorCode.RANK_NOT_FOUND));
    }
}
