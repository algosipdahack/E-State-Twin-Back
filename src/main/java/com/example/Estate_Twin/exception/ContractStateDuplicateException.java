package com.example.Estate_Twin.exception;

import lombok.Getter;

@Getter
public class ContractStateDuplicateException extends RuntimeException {
    private ErrorCode errorCode;

    public ContractStateDuplicateException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
