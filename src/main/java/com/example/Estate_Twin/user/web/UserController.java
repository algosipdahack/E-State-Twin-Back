package com.example.Estate_Twin.user.web;

import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.service.UserService;
import com.example.Estate_Twin.user.web.dto.UserResponseDto;
import com.example.Estate_Twin.user.web.dto.UserSignUpDto;
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

    @Operation(summary = "mypage of user", description = "마이페이지")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))
    })
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponseDto> getcurrentUser(@AuthenticationPrincipal CustomUserDetails user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(user.getId()));
    }

    @Operation(summary = "signup of user", description = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))
    })
    @GetMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@AuthenticationPrincipal CustomUserDetails user, UserSignUpDto userSignUpDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(user.getId(),userSignUpDto));
    }

}
