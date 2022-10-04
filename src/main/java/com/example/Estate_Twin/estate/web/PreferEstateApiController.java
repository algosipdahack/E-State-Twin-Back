package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.estate.domain.entity.Preference;
import com.example.Estate_Twin.estate.service.impl.PreferEstateServiceImpl;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PreferEstate", description = "즐겨찾기 매물 API")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/preferestate/")
public class PreferEstateApiController {
    private final PreferEstateServiceImpl preferEstateService;

    @Operation(summary = "get list of Estate", description = "최근 본 방 목록 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateListResponseDto.class)))})
    @GetMapping("list/recent")
    public ResponseEntity<List<EstateListResponseDto>> getRecentList(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        List<EstateListResponseDto> estateListResponseDtos = preferEstateService.getPreferEstate(user.getEmail(), Preference.RECENT);
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

    @Operation(summary = "get list of Estate", description = "찜한 방 목록 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateListResponseDto.class)))})
    @GetMapping("list/dip")
    public ResponseEntity<List<EstateListResponseDto>> getDipList(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        List<EstateListResponseDto> estateListResponseDtos = preferEstateService.getPreferEstate(user.getEmail(), Preference.DIP);
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

    @Operation(summary = "get list of Estate", description = "문의한 방 목록 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateListResponseDto.class)))})
    @GetMapping("list/inquiry")
    public ResponseEntity<List<EstateListResponseDto>> getInquireList(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        List<EstateListResponseDto> estateListResponseDtos = preferEstateService.getPreferEstate(user.getEmail(), Preference.INQUIRY);
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

}
