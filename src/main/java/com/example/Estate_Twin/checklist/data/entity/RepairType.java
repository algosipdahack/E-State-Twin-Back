package com.example.Estate_Twin.checklist.data.entity;

import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import lombok.Getter;

import java.util.Arrays;
@Getter
public enum RepairType {
        PURCHASE("PURCHASE"), REPAIR("REPAIR");
        private final String type;
        RepairType(String type) {
                this.type = type;
        }
        public static RepairType of(String type) {
                return Arrays.stream(RepairType.values())
                        .filter(v -> v.getType().equals(type))
                        .findAny()
                        .orElseThrow(()-> new CheckHouseException(ErrorCode.REPAIR_TYPE_NOT_FOUND));
        }
}
