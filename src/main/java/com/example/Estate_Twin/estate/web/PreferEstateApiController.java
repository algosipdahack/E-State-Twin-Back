package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.estate.domain.entity.Preference;
import com.example.Estate_Twin.estate.service.impl.PreferEstateServiceImpl;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.util.CurrentUser;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(name = "PreferEstate", description = "즐겨찾기 매물 API")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/preferestate/")
public class PreferEstateApiController {
    private final PreferEstateServiceImpl preferEstateService;

    @Operation(summary = "get list of Estate", description = "최근 본 방 목록 가져오기")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EstateListResponseDto.class)))})})
    @GetMapping("list/recent")
    public ResponseEntity<Page<EstateListResponseDto>> getRecentList(@Parameter(hidden = true) @CurrentUser User user,
                                                                     Pageable pageable) {
        Page<EstateListResponseDto> estateListResponseDtos = preferEstateService.getPreferEstate(user, Preference.RECENT, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

    @Operation(summary = "get list of Estate", description = "찜한 방 목록 가져오기")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EstateListResponseDto.class)))})})
    @GetMapping("list/dip")
    public ResponseEntity<Page<EstateListResponseDto>> getDipList(@Parameter(hidden = true) @CurrentUser User user,
                                                                  Pageable pageable) {
        Page<EstateListResponseDto> estateListResponseDtos = preferEstateService.getPreferEstate(user, Preference.DIP, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

    @Operation(summary = "get list of Estate", description = "문의한 방 목록 가져오기")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EstateListResponseDto.class)))})})
    @GetMapping("list/inquiry")
    public ResponseEntity<Page<EstateListResponseDto>> getInquireList(@Parameter(hidden = true) @CurrentUser User user,
                                                                      Pageable pageable) {
        Page<EstateListResponseDto> estateListResponseDtos = preferEstateService.getPreferEstate(user, Preference.INQUIRY, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

}
