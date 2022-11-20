package com.example.Estate_Twin.auth.controller;

import com.example.Estate_Twin.auth.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Token", description = "인증 api")
@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class Authcontroller {
    private final JwtService jwtService;

    @Operation(summary = "refresh of token", description = "Access/Refresh token 재발급")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = String.class)))})
    @PostMapping("refresh")
    public ResponseEntity<String> refreshToken(@RequestParam(name = "refresh_token") String refreshToken, @RequestParam(name = "access_token") String accessToken) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtService.refreshToken(refreshToken, accessToken));
    }

}
