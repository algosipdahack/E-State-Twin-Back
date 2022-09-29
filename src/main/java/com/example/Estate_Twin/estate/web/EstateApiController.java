package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.address.web.dto.AddressSaveRequestDto;
import com.example.Estate_Twin.estate.service.*;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import io.swagger.annotations.ApiParam;
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

@Tag(name = "Estate", description = "매물 API")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/estate/")
public class EstateApiController {
    private final EstateService estateService;
    private final DipEstateService dipEstateService;

    //리스트
    //TODO 페이징 처리
    @Operation(summary = "get list of Estate", description = "매물 목록 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateListResponseDto.class)))})
    @GetMapping("list")
    public ResponseEntity<List<EstateListResponseDto>> getList() {
        List<EstateListResponseDto> estateListResponseDtos = estateService.getAllEstate();
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

    //추천매물 보여주기 -> 조회수에 따라 정렬
    //TODO 페이징 처리
    @Operation(summary = "get Recommendation of Estate", description = "00구 추천매물 정보 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateMainDto.class)))})
    @GetMapping("main")
    public ResponseEntity<List<EstateMainDto>> getList(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        List<EstateMainDto> estateListResponseDtos = estateService.getEstateCustomized(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

    //상세 페이지
    @Operation(summary = "get detail of Estate", description = "매물에 대한 상세정보들 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @GetMapping("detail/{estateId}")
    public ResponseEntity<EstateResponseDto> getEstate(@PathVariable Long estateId) {
        EstateResponseDto estateResponseDto = estateService.getEstate(estateId);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    @Operation(summary = "Enroll estate", description = "매물 등록하기(Owner)")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @PostMapping("detail/owner")
    public ResponseEntity<Long> postEstateOwner(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user,
                                                             @RequestBody AddressSaveRequestDto addressSaveRequestDto,
                                                             @ApiParam(value = "Broker Id", required = true, example = "1")
                                                             @RequestParam(name = "brokerId") Long brokerId) {
        //owner 매핑, estate 생성, broker매핑
        Long estateId = estateService.saveFirst(addressSaveRequestDto, brokerId, user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(estateId);
    }
    //브로커만
    //TODO
    @Operation(summary = "post detail of Estate", description = "매물에 대한 상세정보들 등록하기(임시저장)")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @PostMapping(value = "detail/broker")
    public ResponseEntity<EstateResponseDto> saveEstate(@ModelAttribute EstateSaveRequestDto estateSaveRequestDto) {
        EstateResponseDto estateDto = estateService.saveEstate(estateSaveRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(estateDto);
    }

    @Operation(summary = "put detail of Estate", description = "매물에 대한 상세정보들 수정하기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PutMapping("detail/{estateId}")
    public ResponseEntity<EstateResponseDto> updateEstate(@PathVariable Long estateId, @RequestBody EstateUpdateRequestDto estateUpdateRequestDto) {
        EstateResponseDto estateResponseDto = estateService.updateEstate(estateId,estateUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    @Operation(summary = "Confirm of estate post", description = "중개인/집주인의 매물 등록 확인")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PutMapping("detail/{estateId}/confirm")
    public ResponseEntity<EstateResponseDto> confirmEstate(@PathVariable Long estateId, @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        EstateResponseDto estateResponseDto = estateService.allowPost(estateId,user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }


    //TODO 세입자 등록, 집주인 매칭, 브로커 매칭
    /*@Operation(summary = "enroll tanent of estate", description = "세입자 등록")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PutMapping("detail/{estateId}/matchTanent")
    public ResponseEntity<EstateResponseDto> enrollTanent(@PathVariable Long estateId, @RequestBody EstateUpdateRequestDto estateUpdateRequestDto) {
        EstateResponseDto estateResponseDto = estateService.updateEstate(estateId,estateUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }*/


    /*@Operation(summary = "dip of Estate", description = "매물 찜하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = DipEstateResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "estateId", description = "Estate Id", example = "1")
    })
    @PostMapping("detail/{estateId}/dip")
    public ResponseEntity<DipEstateResponseDto> dipEstate(@PathVariable Long estateId) {
        DipEstateResponseDto dipEstateResponseDto = DipEstateService.dipEstate(estateId);
        return ResponseEntity.status(HttpStatus.OK).body(dipEstateResponseDto);
    }*/

}
