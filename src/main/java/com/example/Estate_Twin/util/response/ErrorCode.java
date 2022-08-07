package com.example.Estate_Twin.util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    FAIL(999,"실패"),
    NOT_SUPPORTED_METHOD(998, "NOT_SUPPORTED_METHOD"),
    NOT_FOUND_USER(100, "NOT_FOUND_USER"),
    NOT_FOUND_TOKEN(101, "NOT_FOUND_TOKEN"),
    NOT_FOUND_ERROR(102, "MALFORMED_ERROR")
    ;
    private int code;
    private String message;
}
