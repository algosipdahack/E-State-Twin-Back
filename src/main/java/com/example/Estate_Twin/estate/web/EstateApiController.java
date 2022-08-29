package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Estate", description = "매물 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estate/")
public class EstateApiController {
    private final EstateService estateService;

    //리스트
    @Operation(summary = "get list of Estate", description = "매물 목록 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateListResponseDto.class)))
    })
    @GetMapping("list")
    public ResponseEntity<List<EstateListResponseDto>> getList() {
        List<EstateListResponseDto> estateListResponseDtos = estateService.getAllEstate();
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

    //추천매물 보여주기 -> 조회수에 따라 정렬
    @Operation(summary = "get Recommendation of Estate", description = "00구 추천매물 정보 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateListResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "distinct", description = "Name of Distinct", example = "강남구")
    })
    @GetMapping("customized")
    public ResponseEntity<List<EstateListResponseDto>> getList(@RequestParam(value = "distinct") String borough) {
        List<EstateListResponseDto> estateListResponseDtos = estateService.getEstateCustomized(borough);
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

    //상세 페이지
    @Operation(summary = "get detail of Estate", description = "매물에 대한 상세정보들 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "estateId", description = "Estate Id", example = "1")
    })
    @GetMapping("detail/{estateId}")
    public ResponseEntity<EstateResponseDto> getEstate(@PathVariable Long estateId) {
        EstateResponseDto estateResponseDto = estateService.getEstate(estateId);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    @Operation(summary = "post detail of Estate", description = "매물에 대한 상세정보들 등록하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "houseId", description = "House Id", example = "1")
    })
    @PostMapping("detail/{houseId}")
    public ResponseEntity<EstateResponseDto> saveEstate(@PathVariable Long houseId, @RequestBody EstateSaveRequestDto estateSaveRequestDto) {
        EstateResponseDto estateResponseDto = estateService.saveEstate(estateSaveRequestDto,houseId);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    @Operation(summary = "post detail of Estate", description = "매물에 대한 상세정보들 수정하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "estateId", description = "Estate Id", example = "1")
    })
    @PutMapping("detail/{estateId}")
    public ResponseEntity<EstateResponseDto> updateEstate(@PathVariable Long estateId, @RequestBody EstateUpdateRequestDto estateUpdateRequestDto
    ) {
        EstateResponseDto estateResponseDto = estateService.updateEstate(estateId,estateUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    //매물 영상
    //@GetMapping("/detail/{estateId}/video")

    //3D model
    //@GetMapping("/detail/{estateId}/model")

}
