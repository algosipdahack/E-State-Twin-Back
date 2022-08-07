package com.example.Estate_Twin.util.response;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private ApiHeader header;
    private ApiBody body;
    private static int SUCCESS = 200;

    public ApiResponse(ApiHeader header) {
        this.header = header;
    }

    public static <T> ApiResponse<T> SUCCESS(T data) {
        return new ApiResponse<T>(new ApiHeader(SUCCESS, "SUCCESS"), new ApiBody(data,null));
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse(new ApiHeader(errorCode.getCode(),errorCode.name()), new ApiBody(null, errorCode.getMessage()));
    }

}
