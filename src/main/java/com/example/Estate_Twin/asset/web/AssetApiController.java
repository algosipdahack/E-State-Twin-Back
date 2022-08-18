package com.example.Estate_Twin.asset.web;

import com.example.Estate_Twin.asset.service.AssetService;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.asset.web.dto.AssetUpdateRequestDto;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/asset")
public class AssetApiController {
    private final AssetService assetService;

    @GetMapping("/{assetId}")
    public ResponseEntity<AssetResponseDto> getAsset(@PathVariable Long assetId) {
        AssetResponseDto assetResponseDto = assetService.getAsset(assetId);
        return ResponseEntity.status(HttpStatus.OK).body(assetResponseDto);
    }

    @PostMapping("/{houseId}")
    public ResponseEntity<AssetResponseDto> saveAsset(@PathVariable Long houseId, @RequestBody AssetSaveRequestDto assetSaveRequestDto) {
        AssetResponseDto assetResponseDto = assetService.saveAsset(houseId,assetSaveRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(assetResponseDto);
    }

    @PutMapping("/{assetId}")
    public ResponseEntity<AssetResponseDto> updateAsset(@PathVariable Long assetId, @RequestBody AssetUpdateRequestDto assetUpdateRequestDto){
        AssetResponseDto assetResponseDto = assetService.updateAsset(assetId,assetUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(assetResponseDto);
    }
}
