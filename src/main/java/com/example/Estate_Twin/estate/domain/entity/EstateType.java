package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EstateType {
    APARTMENT("APARTMENT"), OFFICETELS("OFFICETELS");
    private final String type;

    EstateType(String type) {
        this.type = type;
    }
    public static EstateType of(String type) {
        return Arrays.stream(EstateType.values())
                .filter(v -> v.getType().equals(type))
                .findAny()
                .orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_TYPE_NOT_FOUND));
    }
}
