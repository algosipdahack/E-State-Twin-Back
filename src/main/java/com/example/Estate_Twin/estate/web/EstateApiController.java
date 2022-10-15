package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.address.*;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.estate.domain.entity.Preference;
import com.example.Estate_Twin.estate.service.impl.*;
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
    private final EstateServiceImpl estateService;
    private final PreferEstateServiceImpl preferEstateService;

    //리스트
    @Operation(summary = "get list of Estate", description = "매물 목록 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateListResponseDto.class)))})
    @GetMapping("list")
    public ResponseEntity<List<EstateListResponseDto>> getList(@RequestParam Long estateId, @RequestParam int size) {
        List<EstateListResponseDto> estateListResponseDtos = estateService.getAllEstate(estateId,size);
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

    //추천매물 보여주기 -> 조회수에 따라 정렬
    @Operation(summary = "get Recommendation of Estate", description = "00구 추천매물 정보 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateMainDto.class)))})
    @GetMapping("main")
    public ResponseEntity<List<EstateMainDto>> getMainPage(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        List<EstateMainDto> estateListResponseDtos = estateService.getEstateCustomized(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

    //상세 페이지
    @Operation(summary = "get detail of Estate", description = "매물에 대한 상세정보들 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @GetMapping("detail/{estateId}")
    public ResponseEntity<EstateDetailDto> getEstate(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user, @PathVariable Long estateId) {
        EstateDetailDto estateDetailDto = estateService.getEstate(estateId, user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(estateDetailDto);
    }

    //집주인이
    @Operation(summary = "Enroll estate", description = "매물 등록하기(Owner)")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @PostMapping("detail/owner")
    public ResponseEntity<Long> postEstateOwner(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user,
                                                @RequestBody Address address,
                                                @ApiParam(value = "Broker Id", required = true, example = "1")
                                                @RequestParam(name = "brokerId") Long brokerId) {
        //owner 매핑, estate 생성, broker매핑
        Long estateId = estateService.saveFirst(address, brokerId, user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(estateId);
    }

    //브로커가
    @Operation(summary = "post detail of Estate", description = "매물에 대한 상세정보들 등록하기(임시저장) - broker")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @PostMapping(value = "detail/broker")
    public ResponseEntity<EstateResponseDto> saveEstate(@RequestBody EstateSaveRequestDto estateSaveRequestDto) {
        EstateResponseDto estateDto = estateService.saveEstate(estateSaveRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(estateDto);
    }

    @Operation(summary = "put detail of Estate", description = "매물에 대한 상세정보들 수정하기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PutMapping("detail/{estateId}")
    public ResponseEntity<EstateResponseDto> updateEstate(@PathVariable Long estateId, @RequestBody EstateUpdateRequestDto estateUpdateRequestDto) {
        EstateResponseDto estateResponseDto = estateService.updateEstate(estateId, estateUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    @Operation(summary = "Confirm of estate post", description = "중개인/집주인의 매물 등록 확인")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PatchMapping("detail/{estateId}/allowPost")
    public ResponseEntity<EstateResponseDto> allowPost(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user, @PathVariable Long estateId) {
        EstateResponseDto estateResponseDto = estateService.allowPost(estateId, user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    //찜하기
    @Operation(summary = "Dip of estate", description = "매물 찜하기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PatchMapping("detail/{estateId}/dip")
    public ResponseEntity<PreferEstateResponseDto> dipEstate(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user, @PathVariable Long estateId) {
        PreferEstateResponseDto preferEstateResponseDto = preferEstateService.savePreferEstate(estateId,user.getEmail(), Preference.DIP);
        return ResponseEntity.status(HttpStatus.OK).body(preferEstateResponseDto);
    }

    //문의하기
    @Operation(summary = "Inquiry of estate", description = "매물 문의하기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PatchMapping("detail/{estateId}/inquiry")
    public ResponseEntity<PreferEstateResponseDto> inquireEstate(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user, @PathVariable Long estateId) {
        PreferEstateResponseDto preferEstateResponseDto = preferEstateService.savePreferEstate(estateId, user.getEmail(), Preference.INQUIRY);
        return ResponseEntity.status(HttpStatus.OK).body(preferEstateResponseDto);
    }


    @Operation(summary = "enroll tenent of estate", description = "계약 진행 버튼 클릭 -> 세입자 등록")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PatchMapping("detail/{estateId}/contract")
    public ResponseEntity<ContractStateResponseDto> startContract(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user, @PathVariable Long estateId) {
        ContractStateResponseDto contractStateResponseDto = estateService.startContract(estateId, user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(contractStateResponseDto);
    }

    // 최근 검색 추가
    @Operation(summary = "show listings by search", description = "검색에 따른 매물 리스트 보여주기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateListResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "페이지 인덱스. 첫페이지일 경우 null", example = "1"), @Parameter(name = "size", description = "페이지 사이즈", example = "1")})
    @PostMapping(value = "list/search")
    public ResponseEntity<List<EstateListResponseDto>> findEstateBySearch(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user, @RequestBody AddressSearchDto addressSearchDto) {
        List<EstateListResponseDto> estateListResponseDtos = estateService.searchEstate(user.getEmail(), addressSearchDto);
        // 최근 본 매물에 추가 -> redis, batch 적용
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

}
