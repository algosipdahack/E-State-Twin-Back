package com.example.Estate_Twin.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //Field Check
    SECURITY_EXCEPTION(HttpStatus.BAD_GATEWAY, "로그인이 필요합니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    INVALID_TOKEN(HttpStatus.NOT_ACCEPTABLE, "유효하지 않은 토큰입니다."),

    MISSING_REQUIRED_VALUE_ERROR(HttpStatus.BAD_REQUEST, "필수 요청값이 누락되었습니다."),
    NOT_ALLOWED_PERMISSION_ERROR(HttpStatus.UNAUTHORIZED, "허용되지 않은 권한입니다."),
    NOT_MATCHED_AUTH_PROVIDER(HttpStatus.BAD_REQUEST, "Auth Provider와 매치되지 않습니다."),

    EMAIL_FORMAT_ERROR(HttpStatus.BAD_REQUEST, "올바르지 않은 이메일 형식입니다."),
    DUPLICATED_EMAIL_ERROR(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    DUPLICATED_CONTRACT_STATE_ERROR(HttpStatus.CONFLICT, "해당 ContractState는 이미 존재합니다."),
    OPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 옵션입니다."),
    ESTATE_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 매물 타입입니다."),
    PREFERENCE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 즐겨찾기 유형입니다."),
    TRANSACTION_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 매물거래 유형입니다."),
    STATE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 매물상태 유형입니다."),
    EMAIL_NOT_FOUND_FROM_PROVIDER(HttpStatus.NOT_FOUND, "해당 provider로는 존재하지 않는 이메일입니다."),


    USER_NOT_OWNER(HttpStatus.METHOD_NOT_ALLOWED, "해당 매물의 집주인이 아닙니다."),
    USER_NOT_TANENT(HttpStatus.METHOD_NOT_ALLOWED, "해당 매물의 세입자가 아닙니다."),
    USER_NOT_BROKER(HttpStatus.METHOD_NOT_ALLOWED, "해당 매물의 브로커가 아닙니다."),
    GRADE_NOT_ALLOW_FOR_NOT_POSTED(HttpStatus.METHOD_NOT_ALLOWED, "게시되지 않은 매물은 뱃지를 가질 수 없습니다!"),
    BROKER_OR_OWNER_YET_CONFIRMED(HttpStatus.METHOD_NOT_ALLOWED, "브로커와 집주인 모두 confirm을 해야 합니다."),
    INTER_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTER SERVER ERROR");

    //Contract
    //Estate
    //Asset
    //User
    //Server

    private final HttpStatus errorCode;
    private final String message;
}
