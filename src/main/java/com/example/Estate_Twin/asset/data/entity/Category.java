package com.example.Estate_Twin.asset.data.entity;

import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Category {
    AIRCONDITIONER("AIRCONDITIONER","HOMEAPPLIANCES"), WASHER("WASHER","BATHROOM"), BED("BED","FURNITURE"), DESK("DESK","FURNITURE"),
    CLOSET("CLOSET","FURNITURE"), TV("TV","HOMEAPPLIANCES"), REFRIGERATOR("REFRIGERATOR","HOMEAPPLIANCES"),
    SHOERACK("SHOERACK","FURNITURE"),
    GASSTOVE("GASSTOVE","HOMEAPPLIANCES"), DOORLOCK("DOORLOCK","FURNITURE"), BIDET("BIDET","BATHROOM"), WALLPAPER("WALLPAPER","INTERIOR"),
    CURTAIN("CURTAIN","INTERIOR"), INDUCTION("INDUCTION","HOMEAPPLIANCES"), MICROWAVE("MICROWAVE","HOMEAPPLIANCES");
    private final String type;
    private final String category;
    Category(String type,String category) {
        this.type = type;
        this.category = category;
    }

    public static Category of(String type) {
        return Arrays.stream(Category.values())
                .filter(v -> v.getType().equals(type))
                .findAny()
                .orElseThrow(() -> new CheckHouseException(ErrorCode.OPTION_NOT_FOUND));
    }
}
