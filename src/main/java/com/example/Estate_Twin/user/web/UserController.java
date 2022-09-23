package com.example.Estate_Twin.user.web;

import com.example.Estate_Twin.auth.jwt.Token;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.service.UserService;
import com.example.Estate_Twin.user.service.impl.OAuthService;
import com.example.Estate_Twin.user.web.dto.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "유저 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final OAuthService oAuthService;

    @Operation(summary = "mypage of user", description = "마이페이지")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))})
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponseDto> getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) {
        UserResponseDto userResponseDto = userService.getUserbyEmail(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @Operation(summary = "signup of user", description = "회원가입")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))})
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@AuthenticationPrincipal CustomUserDetails user, UserSignUpDto userSignUpDto) {
        UserResponseDto userResponseDto = userService.signUp(user.getEmail(), userSignUpDto);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @Operation(summary = "login of user", description = "로그인")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Token.class)))})
    @Parameters({@Parameter(name = "provider", description = "Name of provider", example = "kakao, naver, google")})
    @PostMapping("/login/oauth/{provider}")
    public ResponseEntity<Token> login(@PathVariable String provider, @RequestBody String code) {
        Token token = oAuthService.login(provider, code);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
