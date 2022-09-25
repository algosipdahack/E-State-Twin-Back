package com.example.Estate_Twin.contractstate.domain.entity;

import lombok.Getter;

import java.util.Arrays;
@Getter
public enum State {
    //브로커 승인 전, 등록중인 매물, post완료(매물 post는 완료), 방문 예약, 계약 요청, 중개인 계약요청 확인, 임대인 계약 요청 확인, 특이사항 작성중, 거래 완료
    BROKER_BEFORE("BROKER_BEFORE"),POST_DOING("POST_DOING"),POST_DONE("POST_DONE"),
    CONTRACT_REQUEST("CONTRACT_REQUEST"), CONFIRM_BROKER("CONFIRM_BROKER"), CONFIRM_OWNER("CONFIRM_OWNER"),
    CHECKLIST_DOING("CHECKLIST_DOING"),CONTRACT_DONE("CONTRACT_DONE");
    private final String state;
    State(String state) { this.state = state; }

    public static State of(String state) {
        return Arrays.stream(State.values())
                .filter(v->v.getState().equals(state))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(String.format("매물 상태 유형에 %s가 존재하지 않습니다",state)));


    }
}
