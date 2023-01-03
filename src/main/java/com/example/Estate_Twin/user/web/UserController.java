package com.example.Estate_Twin.user.web;

import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.auth.jwt.Token;
import com.example.Estate_Twin.estate.web.dto.EstateModeDto;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.service.impl.*;
import com.example.Estate_Twin.user.web.dto.*;
import com.example.Estate_Twin.util.CurrentUser;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "User", description = "유저 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserServiceImpl userService;

    @Operation(summary = "signup of user", description = "회원가입")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = UserInfoDto.class)))
    @PostMapping("/signup")
    public ResponseEntity<UserInfoDto> signup(@Parameter(hidden = true) @CurrentUser User user,
                                              @RequestBody @Valid UserSignUpDto userSignUpDto) {
        UserInfoDto userInfoDto = userService.signUp(user, userSignUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userInfoDto);
    }

    @Operation(summary = "mypage of user", description = "마이페이지")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserInfoDto.class)))
    @GetMapping("/me")
    public ResponseEntity<UserInfoDto> getCurrentUser(@Parameter(hidden = true) @CurrentUser User user) {
        return ResponseEntity.status(HttpStatus.OK).body(new UserInfoDto(user));
    }

    @Operation(summary = "login of user", description = "로그인")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Token.class)))
    @Parameter(name = "provider", description = "Name of provider", example = "kakao, naver, google")
    @PostMapping("/login/oauth/{provider}")
    public ResponseEntity<Token> login(@PathVariable String provider, @RequestParam String code) {
        if(provider != "kakao" || provider != "naver" || provider != "google") {
            throw new CheckHouseException(ErrorCode.PROVIDER_NOT_FOUND);
        }
        Token token = userService.login(provider, code);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @Operation(summary = "mypage list of tenant", description = "세입자모드 목록")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateModeDto.class)))
    @GetMapping("/tenant")
    public ResponseEntity<EstateModeDto> getUserAssetList(@Parameter(hidden = true) @CurrentUser User user) {
        EstateModeDto estate = userService.getTenantAssetList(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(estate);
    }

    @Operation(summary = "mypage detail of tenant", description = "세입자모드 상세")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AssetResponseDto.class)))})})
    @GetMapping("/tenant/detail")
    public ResponseEntity<List<AssetResponseDto>> getUserAsset(@Parameter(hidden = true) @CurrentUser User user,
                                                               @RequestParam(name = "category") String category) {
        List<AssetResponseDto> assets = userService.getTenantAsset(user.getId(), Category.of(category));
        return ResponseEntity.status(HttpStatus.OK).body(assets);
    }

    @Operation(summary = "mypage of owner", description = "집주인 모드 목록")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EstateModeDto.class)))})})
    @GetMapping("/owner")
    public ResponseEntity<List<EstateModeDto>> getOwnerHouseList(@Parameter(hidden = true) @CurrentUser User user) {
        List<EstateModeDto> assetList = userService.getOwnerAssetList(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(assetList);
    }

    @Operation(summary = "mypage detail of owner", description = "집주인 모드 상세")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AssetResponseDto.class)))})})
    @GetMapping("/owner/detail")
    public ResponseEntity<List<AssetResponseDto>> getOwnerHouse(@Parameter(hidden = true) @CurrentUser User user,
                                                                @RequestParam(name = "estateId") Long estateId) {
        List<AssetResponseDto> ownerAsset = userService.getOwnerAsset(user.getId(), estateId);
        return ResponseEntity.status(HttpStatus.OK).body(ownerAsset);
    }

    @Operation(summary = "logout of user", description = "로그아웃")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        // 세션 삭제
        SecurityContextHolder.clearContext();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 성공!");
    }

    @Operation(summary = "Membership Withdrawal", description = "회원탈퇴")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Long.class)))
    @DeleteMapping("")
    public ResponseEntity<Long> withdrawal(@Parameter(hidden = true) @CurrentUser User user) {
        Long userId = userService.deleteUser(user);
        SecurityContextHolder.clearContext();
        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }

}
