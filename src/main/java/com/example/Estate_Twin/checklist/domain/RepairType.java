package com.example.Estate_Twin.checklist.domain;

import lombok.Getter;

import java.util.Arrays;
@Getter
public enum RepairType {
        PURCHASE("purchase"), REPAIR("repair");
        private final String type;
        RepairType(String type) {
                this.type = type;
        }
        public static RepairType of(String type) {
                return Arrays.stream(RepairType.values())
                        .filter(v -> v.getType().equals(type))
                        .findAny()
                        .orElseThrow(()-> new IllegalArgumentException(String.format("에셋 변경 사항 유형에 %s 가 존재하지 않습니다.",type)));
        }
}
