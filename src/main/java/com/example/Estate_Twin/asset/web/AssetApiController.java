package com.example.Estate_Twin.asset.web;

import com.example.Estate_Twin.asset.service.AssetService;
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
    private final AssetService assetService;

    @Operation(summary = "get assets", description = "에셋에 대한 정보들 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AssetResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "assetId", description = "Asset Id", example = "1")
    })
    @GetMapping("/{assetId}")
    public ResponseEntity<AssetResponseDto> getAsset(@PathVariable Long assetId) {
        AssetResponseDto assetResponseDto = assetService.getAsset(assetId);
        return ResponseEntity.status(HttpStatus.OK).body(assetResponseDto);
    }

    @Operation(summary = "get assets", description = "에셋 등록하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AssetResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "houseId", description = "House Id", example = "1")
    })
    @PostMapping("/{houseId}")
    public ResponseEntity<AssetResponseDto> saveAsset(@PathVariable Long houseId, @RequestBody AssetSaveRequestDto assetSaveRequestDto) {
        AssetResponseDto assetResponseDto = assetService.saveAsset(houseId,assetSaveRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(assetResponseDto);
    }

    @Operation(summary = "put assets", description = "에셋에 대한 정보들 수정하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AssetResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "assetId", description = "Asset Id", example = "1")
    })
    @PutMapping("/{assetId}")
    public ResponseEntity<AssetResponseDto> updateAsset(@PathVariable Long assetId, @RequestBody AssetUpdateRequestDto assetUpdateRequestDto){
        AssetResponseDto assetResponseDto = assetService.updateAsset(assetId,assetUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(assetResponseDto);
    }
}
