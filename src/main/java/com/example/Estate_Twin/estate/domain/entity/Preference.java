package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Preference {
    //찜한방, 문의한방(ARCam), 최근 본 방
    DIP("DIP"), RECENT("RECENT"), INQUIRY("INQUIRY");
    private final String prefer;
    Preference(String prefer) {
        this.prefer = prefer;
    }
    public static Preference of(String prefer) {
        return Arrays.stream(Preference.values())
                .filter(v -> v.getPrefer().equals(prefer))
                .findAny()
                .orElseThrow(()-> new CheckHouseException(ErrorCode.PREFERENCE_NOT_FOUND));
    }
}
