package com.example.Estate_Twin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CheckHouseException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final ExceptionResponse body;
    public CheckHouseException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = httpStatus;
        this.body = new ExceptionResponse(errorCode.getErrorCode(), errorCode.getMessage());
    }
    public CheckHouseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = errorCode.getErrorCode();
        this.body = new ExceptionResponse(errorCode.getErrorCode(), errorCode.getMessage());
    }
    public CheckHouseException(ErrorCode errorCode, String message) {
        super(errorCode.getMessage());
        this.httpStatus = errorCode.getErrorCode();
        this.body = new ExceptionResponse(errorCode.getErrorCode(), message);
    }
}
