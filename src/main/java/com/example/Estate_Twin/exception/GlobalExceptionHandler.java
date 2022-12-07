package com.example.Estate_Twin.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CheckHouseException.class)
    public ResponseEntity<ExceptionResponse> handleCheckHouseException(CheckHouseException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getBody());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MethodArgumentNotValidExceptionResponse> handleMethodNotValidException(MethodArgumentNotValidException ex) {
        MethodArgumentNotValidExceptionResponse exceptionResponse = MethodArgumentNotValidExceptionResponse.of(ErrorCode.MISSING_REQUIRED_VALUE_ERROR);
        for (FieldError fieldError : ex.getFieldErrors()) {
            exceptionResponse.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        String unexpectedErrorTrace = ExceptionUtils.getStackTrace(ex);
        log.error(unexpectedErrorTrace);
        return ResponseEntity.internalServerError()
                .body(
                        new ExceptionResponse(
                                ErrorCode.INTER_SERVER_ERROR.getErrorCode(), ErrorCode.INTER_SERVER_ERROR.getMessage()
                        )
                );
    }

}
