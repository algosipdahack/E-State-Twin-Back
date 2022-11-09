package com.example.Estate_Twin.user.web;

import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.asset.data.entity.Option;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.auth.jwt.Token;
import com.example.Estate_Twin.estate.web.dto.BrokerEstateDto;
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
    private final RedisService redisService;

    @Operation(summary = "mypage of user", description = "마이페이지")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserInfoDto.class)))})
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserInfoDto> getCurrentUser(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        UserInfoDto userInfoDto = userService.getUser(customUserDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(userInfoDto);
    }

    @Operation(summary = "mypage of tenant", description = "세입자모드 목록")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateModeDto.class)))})
    @GetMapping("/tenant/list")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EstateModeDto> getUserAssetList(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        EstateModeDto estate = userService.getTenantAssetList(customUserDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(estate);
    }

    @Operation(summary = "mypage detail of tenant", description = "세입자모드 상세")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AssetResponseDto.class)))})})
    @GetMapping("/tenant/detail")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<AssetResponseDto>> getUserAsset(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam(name = "category") String category) {
        List<AssetResponseDto> assets = userService.getTenantAsset(customUserDetails.getUser().getId(), Category.of(category));
        return ResponseEntity.status(HttpStatus.OK).body(assets);
    }

    @Operation(summary = "mypage of owner", description = "집주인 모드 목록")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EstateModeDto.class)))})})
    @GetMapping("/owner/list")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<EstateModeDto>> getOwnerHouseList(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<EstateModeDto> assetList = userService.getOwnerAssetList(customUserDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(assetList);
    }

    @Operation(summary = "mypage detail of owner", description = "집주인 모드 상세")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AssetResponseDto.class)))})})
    @GetMapping("/owner/detail")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<AssetResponseDto>> getOwnerHouse(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam(name = "category") String category) {
        List<AssetResponseDto> ownerAsset = userService.getOwnerAsset(customUserDetails.getUser().getId(), Category.of(category));
        return ResponseEntity.status(HttpStatus.OK).body(ownerAsset);
    }

    @Operation(summary = "signup of user", description = "회원가입")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserInfoDto.class)))})
    @PostMapping("/signup")
    public ResponseEntity<UserInfoDto> signup(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody UserSignUpDto userSignUpDto) {
        UserInfoDto userInfoDto = userService.signUp(customUserDetails.getUser(), userSignUpDto);
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
    public ResponseEntity logout(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        // refresh Token 삭제
        redisService.delValues(customUserDetails.getEmail());
        // 세션 삭제
        SecurityContextHolder.clearContext();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 성공!");
    }

    @Operation(summary = "Membership Withdrawal", description = "회원탈퇴")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Long.class)))})
    @DeleteMapping("")
    public ResponseEntity<Long> withdrawal(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = userService.deleteUser(customUserDetails.getUser());
        SecurityContextHolder.clearContext();
        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }

}
