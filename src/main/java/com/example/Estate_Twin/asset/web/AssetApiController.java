package com.example.Estate_Twin.asset.web;

import com.example.Estate_Twin.asset.service.AssetService;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.asset.web.dto.AssetSaveRequestDto;
import com.example.Estate_Twin.asset.web.dto.AssetUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/asset")
public class AssetApiController {
    private final AssetService assetService;

    @GetMapping("/{assetId}")
    public AssetResponseDto getAsset(@PathVariable Long assetId) {
        return assetService.findById(assetId);
    }

    @PostMapping("/{houseId}")
    public Long saveAsset(@PathVariable Long houseId, @RequestBody AssetSaveRequestDto assetSaveRequestDto) {
        return assetService.save(houseId, assetSaveRequestDto);
    }

    @PutMapping("/{assetId}")
    public Long updateAsset(@PathVariable Long assetId, @RequestBody AssetUpdateRequestDto assetUpdateRequestDto){
        return assetService.update(assetId, assetUpdateRequestDto);
    }
}
