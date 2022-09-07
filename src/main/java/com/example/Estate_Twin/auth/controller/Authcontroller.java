package com.example.Estate_Twin.auth.controller;

import com.example.Estate_Twin.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Authcontroller {
    private final AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestParam(name = "refresh_token") String refreshToken,@RequestParam(name = "access_token") String accessToken) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(refreshToken,accessToken));
    }

}
