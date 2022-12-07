package com.example.Estate_Twin.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {
    private final String message;
    private final HttpStatus code;

    public ExceptionResponse(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ExceptionResponse of(ErrorCode errorCode) {
        return new ExceptionResponse(errorCode.getErrorCode(), errorCode.getMessage());
    }

}
