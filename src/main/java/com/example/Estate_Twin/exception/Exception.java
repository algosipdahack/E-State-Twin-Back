package com.example.Estate_Twin.exception;

public class Exception extends RuntimeException{
    public Exception() {
    }

    public Exception(String message) {
        super(message);
    }

    public Exception(String message, Throwable cause) {
        super(message,cause);
    }

    public Exception(Throwable cause){
        super(cause);
    }

    public Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message,cause,enableSuppression,writableStackTrace);
    }
}
