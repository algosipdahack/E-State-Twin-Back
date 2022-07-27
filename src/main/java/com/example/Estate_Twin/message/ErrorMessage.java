package com.example.Estate_Twin.message;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorMessage {
    // Authorization
    public static String UNAUTHORIZED = "접근 권한이 없습니다.";

    // User
    public static String NOT_EXIST_USER = "존재하지 않는 사용자입니다.";

    // Broker
    public static String NOT_EXIST_BROKER = "존재하지 않는 중개인입니다.";
    public static String USER_NOT_BROKER = "사용자가 중개인이 아닙니다.";

    // Estate
    public static String NOT_EXIST_ESTATE = "존재하지 않는 매물 입니다.";

    // EstateInfo
    public static String NOT_EXIST_ESTATE_INFO = "존재하지 않는 매물-info 입니다.";

    // Checklist
    public static String NOT_EXIST_C= "존재하지 않는 체크리스트입니다.";
    public static String FORM_STATE_NOT_VALID = "유효하지 않은 state 값입니다.";
    public static String FORM_IS_NOT_BELONG_TO_THIS_LOGIN_USER = "이 신청서는 로그인한 사용자 앞으로 작성되지 않았습니다.";
}