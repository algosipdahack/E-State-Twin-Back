package com.example.Estate_Twin.user.web;

import com.example.Estate_Twin.asset.data.entity.Option;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.auth.jwt.Token;
import com.example.Estate_Twin.estate.web.dto.EstateModeDto;
import com.example.Estate_Twin.redis.RedisService;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.service.impl.*;
import com.example.Estate_Twin.user.web.dto.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "유저 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserServiceImpl userService;
    private final OAuthService oAuthService;
    //private final RedisService redisService;

    // TODO - 아직 무슨 내용이 들어가야 할지 모름
    @Operation(summary = "mypage of user", description = "마이페이지")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))})
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponseDto> getCurrentUser(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        UserResponseDto userResponseDto = userService.getUserbyEmail(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @Operation(summary = "mypage of tenent", description = "세입자모드 목록")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateModeDto.class)))})
    @GetMapping("/tenent/list")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EstateModeDto> getUserAssetList(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        EstateModeDto estate = userService.getTenentAssetList(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(estate);
    }

    @Operation(summary = "mypage detail of tenent", description = "세입자모드 상세")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AssetResponseDto.class)))})
    @GetMapping("/tenent/detail")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<AssetResponseDto>> getUserAsset(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user, @RequestParam(name = "option") String option) {
        List<AssetResponseDto> assets = userService.getTenentAsset(user.getId(), Option.of(option));
        return ResponseEntity.status(HttpStatus.OK).body(assets);
    }

    @Operation(summary = "mypage of owner", description = "집주인 모드 목록")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateModeDto.class)))})
    @GetMapping("/owner/list")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<EstateModeDto>> getOwnerHouseList(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        List<EstateModeDto> assetList = userService.getOwnerAssetList(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(assetList);
    }

    @Operation(summary = "mypage detail of owner", description = "집주인 모드 상세")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))})
    @GetMapping("/owner/detail")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<AssetResponseDto>> getOwnerHouse(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user, @RequestParam(name = "option") String option) {
        List<AssetResponseDto> ownerAsset = userService.getOwnerAsset(user.getId(), Option.of(option));
        return ResponseEntity.status(HttpStatus.OK).body(ownerAsset);
    }

    @Operation(summary = "signup of user", description = "회원가입")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserResponseDto.class)))})
    @PostMapping("/signup")
    public ResponseEntity<UserInfoDto> signup(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user, @RequestBody UserSignUpDto userSignUpDto) {
        UserInfoDto userInfoDto = userService.signUp(user.getEmail(), userSignUpDto);
        return ResponseEntity.status(HttpStatus.OK).body(userInfoDto);
    }

    @Operation(summary = "login of user", description = "로그인")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Token.class)))})
    @Parameters({@Parameter(name = "provider", description = "Name of provider", example = "kakao, naver, google")})
    @PostMapping("/login/oauth/{provider}")
    public ResponseEntity<Token> login(@PathVariable String provider, @RequestBody String code) {
        Token token = oAuthService.login(provider, code);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @Operation(summary = "logout of user", description = "로그아웃")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))})
    @GetMapping("/logout")
    public ResponseEntity logout(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        // refresh Token 삭제
        //redisService.delValues(user.getEmail());
        // 세션 삭제
        SecurityContextHolder.clearContext();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 성공!");
    }

    @Operation(summary = "Membership Withdrawal", description = "회원탈퇴")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Long.class)))})
    @DeleteMapping("")
    public ResponseEntity<Long> withdrawal(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        Long userId = userService.deleteUser(user.getEmail());
        SecurityContextHolder.clearContext();
        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }

}
