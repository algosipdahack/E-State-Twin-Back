package com.example.Estate_Twin.asset.web;

import com.example.Estate_Twin.asset.service.AssetService;
import com.example.Estate_Twin.asset.web.dto.*;
import com.example.Estate_Twin.media.service.AwsS3Service;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Asset", description = "에셋 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/asset")
public class AssetApiController {
    private final AssetService assetService;
    private final AwsS3Service awsS3Service;
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

    /*@Operation(summary = "post assets", description = "에셋 등록하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AssetResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "houseId", description = "House Id", example = "1")
    })
    @PostMapping("")
    public ResponseEntity<AssetResponseDto> saveAsset(@RequestParam("media") List<MultipartFile> multipartFiles, @RequestBody AssetSaveRequestDto assetSaveRequestDto) {
        AssetResponseDto assetDto = assetService.saveAsset(assetSaveRequestDto);
        awsS3Service.uploadAsset(multipartFiles,assetDto.getId(),"asset");
        return ResponseEntity.status(HttpStatus.OK).body(assetDto);
    }*/

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
        awsS3Service.uploadAsset(assetUpdateRequestDto.getAssetPhotos(),assetResponseDto.getId(),"asset");
        return ResponseEntity.status(HttpStatus.OK).body(assetResponseDto);
    }
}
