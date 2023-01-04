package com.example.Estate_Twin.asset.web;

import com.example.Estate_Twin.asset.service.impl.AssetServiceImpl;
import com.example.Estate_Twin.asset.web.dto.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Asset", description = "에셋 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/asset")
public class AssetApiController {
    private final AssetServiceImpl assetService;

    @Operation(summary = "Get Assets", description = "에셋에 대한 정보들 가져오기(매물 등록 후)")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AssetResponseDto.class)))
    @Parameter(name = "assetId", description = "Asset Id", example = "1")
    @GetMapping("/{assetId}")
    public ResponseEntity<AssetResponseDto> getAsset(@PathVariable Long assetId) {
        AssetResponseDto assetResponseDto = assetService.getAsset(assetId);
        return ResponseEntity.status(HttpStatus.OK).body(assetResponseDto);
    }

    @Operation(summary = "Put Asset", description = "에셋에 대한 정보들 수정하기")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AssetResponseDto.class)))
    @Parameter(name = "assetId", description = "Asset Id", example = "1")
    @PutMapping("/{assetId}")
    public ResponseEntity<AssetResponseDto> updateAsset(@PathVariable Long assetId, @RequestBody AssetUpdateRequestDto assetUpdateRequestDto){
        AssetResponseDto assetResponseDto = assetService.updateAsset(assetId, assetUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(assetResponseDto);
    }

    @Operation(summary = "Post Asset", description = "에셋 등록하기")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = AssetResponseDto.class)))
    @PostMapping("/estate/{estateId}")
    public ResponseEntity<AssetSummaryDto> saveAsset(@PathVariable Long estateId, @RequestBody AssetSaveRequestDto assetSaveRequestDto) {
        AssetSummaryDto assetDto = assetService.saveAsset(estateId, assetSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(assetDto);
    }
}
