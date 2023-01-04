package com.example.Estate_Twin.house.web;

import com.example.Estate_Twin.house.service.impl.HouseServiceImpl;
import com.example.Estate_Twin.house.web.dto.*;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(name = "House", description = "집 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/house")
public class HouseApiController {
    private final HouseServiceImpl houseService;

    @Operation(summary = "Get House", description = "집에 대한 정보들 가져오기")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = HouseResponseDto.class)))
    @GetMapping("/{houseId}")
    public ResponseEntity<HouseResponseDto> getHouse(@ApiParam(value = "house Id") @PathVariable Long houseId) {
        return ResponseEntity.status(HttpStatus.OK).body(houseService.getHouse(houseId));
    }
}
