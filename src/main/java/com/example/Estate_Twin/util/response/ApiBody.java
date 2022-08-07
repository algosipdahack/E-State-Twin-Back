package com.example.Estate_Twin.util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ApiBody <T>{
    private T data;
    private T msg;
}
