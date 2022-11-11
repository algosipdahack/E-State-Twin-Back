package com.example.Estate_Twin.exception;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    CONTRACT_STATE_DUPLICATION(400, "CONTRACT-STATE-ERR-400", "CONTRACT-STATE-DUPLICATED"),
    INTER_SERVER_ERROR(500, "COMMON-ERR-500", "INTER SERVER ERROR");
    private final int status;
    private final String errorCode;
    private final String message;
}
