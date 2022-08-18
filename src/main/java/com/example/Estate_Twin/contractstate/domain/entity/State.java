package com.example.Estate_Twin.contractstate.domain.entity;

import lombok.Getter;

import java.util.Arrays;
@Getter
public enum State {
    BEFORE("before"),AFTER("after"),DOING("doing"),DONE("done");
    private final String state;
    State(String state) { this.state = state; }

    public static State of(String state) {
        return Arrays.stream(State.values())
                .filter(v->v.getState().equals(state))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(String.format("매물 상태 유형에 %s가 존재하지 않습니다",state)));
    }
}
