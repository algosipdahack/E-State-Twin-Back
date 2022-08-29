package com.example.Estate_Twin.house.web;

import com.example.Estate_Twin.house.service.HouseService;
import com.example.Estate_Twin.house.web.dto.*;
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
    private final HouseService houseService;

    @Operation(summary = "get House", description = "집에 대한 정보들 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = HouseResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "houseId", description = "House Id", example = "1")
    })
    @GetMapping("/{houseId}")
    public ResponseEntity<HouseResponseDto> getHouse(@PathVariable Long houseId) {
        return ResponseEntity.status(HttpStatus.OK).body(houseService.getHouse(houseId));
    }

    @Operation(summary = "post House", description = "집에 대한 정보들 등록하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = HouseResponseDto.class)))
    })
    @PostMapping("")
    public ResponseEntity<HouseResponseDto> saveHouse(@RequestBody HouseSaveRequestDto houseSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(houseService.saveHouse(houseSaveRequestDto));
    }

    @Operation(summary = "update House", description = "집에 대한 정보들 수정하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = HouseResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "houseId", description = "House Id", example = "1")
    })
    @PutMapping("/{houseId}")
    public ResponseEntity<HouseResponseDto> updateHouse(@PathVariable Long houseId, @RequestBody HouseUpdateRequestDto houseUpdateRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(houseService.updateHouse(houseId, houseUpdateRequestDto));
    }

}
