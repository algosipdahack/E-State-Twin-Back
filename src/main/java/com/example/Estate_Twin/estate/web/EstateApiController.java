package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.address.*;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.estate.domain.entity.Preference;
import com.example.Estate_Twin.estate.service.impl.*;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.util.CurrentUser;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Estate", description = "매물 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estate/")
public class EstateApiController {
    private final EstateServiceImpl estateService;
    private final PreferEstateServiceImpl preferEstateService;

    //리스트
    @Operation(summary = "Get List of Estate", description = "매물 목록 가져오기")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EstateListResponseDto.class)))})})
    @GetMapping("list")
    public ResponseEntity<Page<EstateListResponseDto>> getList(@RequestParam(required = false) Long estateId,
                                                               @PageableDefault(size = 10, sort = "estateId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<EstateListResponseDto> estateListResponseDtos = estateService.getAllEstate(estateId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

    //추천매물 보여주기 -> 조회수에 따라 정렬
    @Operation(summary = "Get Recommendation of Estate", description = "00구 추천매물 정보 가져오기")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EstateMainDto.class)))})})
    @GetMapping("main")
    public ResponseEntity<List<EstateMainDto>> getMainPage(@Parameter(hidden = true) @CurrentUser User user) {
        List<EstateMainDto> estateListResponseDtos = estateService.getEstateCustomized(user);
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

    //상세 페이지
    @Operation(summary = "Get Detail of Estate", description = "매물에 대한 상세정보들 가져오기")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateDetailDto.class)))
    @Parameter(name = "estateId", description = "Estate Id", example = "1")
    @GetMapping("{estateId}")
    public ResponseEntity<EstateDetailDto> getEstate(@Parameter(hidden = true) @CurrentUser User user,
                                                     @PathVariable Long estateId) {
        EstateDetailDto estateDetailDto = estateService.getEstate(estateId, user);
        return ResponseEntity.status(HttpStatus.OK).body(estateDetailDto);
    }

    //집주인이
    @Operation(summary = "Post Estate - Mapping Broker", description = "매물 등록하기(Owner)")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = Long.class)))
    @PostMapping("owner")
    public ResponseEntity<Long> postEstateOwner(@Parameter(hidden = true) @CurrentUser User user,
                                                @RequestBody Address address,
                                                @ApiParam(value = "Broker Id", required = true, example = "1")
                                                @RequestParam(name = "brokerId") Long brokerId) {
        //owner 매핑, estate 생성, broker매핑
        Long estateId = estateService.saveFirst(address, brokerId, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(estateId);
    }

    //브로커가
    @Operation(summary = "Post Detail of Estate", description = "매물에 대한 상세정보들 등록하기(임시저장) - broker")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))
    @PostMapping(value = "broker")
    public ResponseEntity<EstateResponseDto> saveEstate(@RequestBody EstateSaveRequestDto estateSaveRequestDto) {
        EstateResponseDto estateDto = estateService.saveEstate(estateSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(estateDto);
    }

    @Operation(summary = "Put Detail of Estate", description = "매물에 대한 상세정보들 수정하기")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))
    @Parameter(name = "estateId", description = "Estate Id", example = "1")
    @PutMapping("{estateId}")
    public ResponseEntity<EstateResponseDto> updateEstate(@PathVariable Long estateId,
                                                          @RequestBody EstateUpdateRequestDto estateUpdateRequestDto) {
        EstateResponseDto estateResponseDto = estateService.updateEstate(estateId, estateUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    @Operation(summary = "Confirm of Estate Post", description = "중개인/집주인의 매물 등록 확인")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))
    @Parameter(name = "estateId", description = "Estate Id", example = "1")
    @PatchMapping("{estateId}/confirm")
    public ResponseEntity<EstateResponseDto> allowPost(@Parameter(hidden = true) @CurrentUser User user,
                                                       @PathVariable Long estateId) {
        EstateResponseDto estateResponseDto = estateService.allowPost(estateId, user);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    //찜하기
    @Operation(summary = "Dip of Estate", description = "매물 찜하기")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))
    @Parameter(name = "estateId", description = "Estate Id", example = "1")
    @PatchMapping("{estateId}/dip")
    public ResponseEntity<PreferEstateResponseDto> dipEstate(@Parameter(hidden = true) @CurrentUser User user,
                                                             @PathVariable Long estateId) {
        PreferEstateResponseDto preferEstateResponseDto = preferEstateService.savePreferEstate(estateId, user, Preference.DIP);
        return ResponseEntity.status(HttpStatus.OK).body(preferEstateResponseDto);
    }

    //문의하기
    @Operation(summary = "Inquiry of Estate", description = "매물 문의하기")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PreferEstateResponseDto.class)))
    @Parameter(name = "estateId", description = "Estate Id", example = "1")
    @PatchMapping("{estateId}/inquiry")
    public ResponseEntity<PreferEstateResponseDto> inquireEstate(@Parameter(hidden = true) @CurrentUser User user,
                                                                 @PathVariable Long estateId) {
        PreferEstateResponseDto preferEstateResponseDto = preferEstateService.savePreferEstate(estateId, user, Preference.INQUIRY);
        return ResponseEntity.status(HttpStatus.OK).body(preferEstateResponseDto);
    }


    @Operation(summary = "Enroll Tenant of Estate", description = "계약 진행 버튼 클릭 -> 세입자 등록")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ContractStateResponseDto.class)))
    @Parameter(name = "estateId", description = "Estate Id", example = "1")
    @PatchMapping("{estateId}/contract")
    public ResponseEntity<ContractStateResponseDto> startContract(@Parameter(hidden = true) @CurrentUser User user,
                                                                  @PathVariable Long estateId) {
        ContractStateResponseDto contractStateResponseDto = estateService.startContract(estateId, user);
        return ResponseEntity.status(HttpStatus.OK).body(contractStateResponseDto);
    }

    // 최근 검색 추가
    @Operation(summary = "Show Lists by Search", description = "검색에 따른 매물 리스트 보여주기")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EstateListResponseDto.class)))})})
    @GetMapping(value = "search")
    public ResponseEntity<List<EstateListResponseDto>> findEstateBySearch(@Parameter(hidden = true) @CurrentUser User user,
                                                                          @RequestParam String borough, @RequestParam String town,
                                                                          Pageable pageable) {
        List<EstateListResponseDto> estateListResponseDtos = estateService.searchEstate(user, borough, town, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(estateListResponseDtos);
    }

}
