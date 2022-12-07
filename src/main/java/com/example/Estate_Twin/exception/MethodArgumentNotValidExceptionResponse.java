package com.example.Estate_Twin.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.*;

@Getter
@RequiredArgsConstructor
public class MethodArgumentNotValidExceptionResponse {
    private final HttpStatus errorCode;
    private final String message;
    private Map<String, String> validation = new HashMap<>();

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }

    public static MethodArgumentNotValidExceptionResponse of(ErrorCode errorCode) {
        return new MethodArgumentNotValidExceptionResponse(errorCode.getErrorCode(), errorCode.getMessage());
    }
}
