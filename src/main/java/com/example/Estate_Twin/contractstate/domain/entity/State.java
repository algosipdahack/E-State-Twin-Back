package com.example.Estate_Twin.contractstate.domain.entity;

import lombok.Getter;

import java.util.Arrays;
@Getter
public enum State {
    //거래 전, 방문 예약 , 계약 진행 버튼 , 거래 완료
    CONTRACT_BEFORE("before"), RESERVATION("reservation"), CONTRACT_DOING("doing"), CONTRACT_DONE("done");
    private final String state;
    State(String state) { this.state = state; }

    public static State of(String state) {
        return Arrays.stream(State.values())
                .filter(v->v.getState().equals(state))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(String.format("매물 상태 유형에 %s가 존재하지 않습니다",state)));


    }
}
