package com.example.Estate_Twin.auth.controller;

import com.example.Estate_Twin.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Authcontroller {
    private final JwtService jwtService;

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestParam(name = "refresh_token") String refreshToken,@RequestParam(name = "access_token") String accessToken) {
        return ResponseEntity.status(HttpStatus.OK).body(jwtService.refreshToken(refreshToken,accessToken));
    }

}
