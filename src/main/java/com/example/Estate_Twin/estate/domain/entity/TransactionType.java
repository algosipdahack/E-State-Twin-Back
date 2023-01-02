package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
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
                .orElseThrow(()-> new CheckHouseException(ErrorCode.TRANSACTION_TYPE_NOT_FOUND));
    }
}
