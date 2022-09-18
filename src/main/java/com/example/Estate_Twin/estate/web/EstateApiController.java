package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.estate.service.*;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.media.service.AwsS3Service;
import com.example.Estate_Twin.media.web.dto.MediaDto;
import com.example.Estate_Twin.media.web.dto.MediaSaveRequestDto;
import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Tag(name = "Estate", description = "매물 API")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/estate/")
public class EstateApiController {
    private final EstateService estateService;
    private final DipEstateService dipEstateService;
    private final AwsS3Service awsS3Service;

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
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateListResponseDto.class)))})
    @Parameters({@Parameter(name = "distinct", description = "Name of Distinct", example = "강남구")})
    @GetMapping("customized")
    public ResponseEntity<List<EstateListResponseDto>> getList(@RequestParam(value = "distinct") String borough) {
        List<EstateListResponseDto> estateListResponseDtos = estateService.getEstateCustomized(borough);
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

    @Operation(summary = "post detail of Estate", description = "매물에 대한 상세정보들 등록하기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @PostMapping(value = "detail")
    public ResponseEntity<EstateResponseDto> saveEstate(@ModelAttribute EstateSaveRequestDto estateSaveRequestDto) {
        EstateResponseDto estateDto = estateService.saveEstate(estateSaveRequestDto);
        awsS3Service.uploadEstate(estateSaveRequestDto.getEstatePhotos(),estateDto.getId(),"estate");
        return ResponseEntity.status(HttpStatus.OK).body(estateDto);
    }

    @Operation(summary = "put detail of Estate", description = "매물에 대한 상세정보들 수정하기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PutMapping("detail/{estateId}")
    public ResponseEntity<EstateResponseDto> updateEstate(@PathVariable Long estateId, @RequestBody EstateUpdateRequestDto estateUpdateRequestDto) {
        EstateResponseDto estateResponseDto = estateService.updateEstate(estateId,estateUpdateRequestDto);
        awsS3Service.uploadEstate(estateUpdateRequestDto.getEstatePhotos(),estateResponseDto.getId(),"estate");
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    //TODO
    @Operation(summary = "Allow broker of estate", description = "중개인의 매물 등록 확인")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PutMapping("detail/{estateId}/postBroker")
    public ResponseEntity<EstateResponseDto> allowBroker(@PathVariable Long estateId, @AuthenticationPrincipal CustomUserDetails user
    ) {
        //user가 해당 매물의 broker가 맞는지 확인
        EstateResponseDto estateResponseDto = estateService.allowPost(estateId,user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    @Operation(summary = "Allow owner of estate", description = "집주인의 매물 등록 확인")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PutMapping("detail/{estateId}/postOwner")
    public ResponseEntity<EstateResponseDto> allowOwner(@PathVariable Long estateId, @AuthenticationPrincipal CustomUserDetails user
    ) {
        //user가 해당 매물의 집주인이 맞는지 확인
        EstateResponseDto estateResponseDto = estateService.allowPost(estateId,user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }
/*
    @Operation(summary = "enroll tanent of estate", description = "세입자 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))
    })
    @Parameters({
            @Parameter(name = "estateId", description = "Estate Id", example = "1")
    })
    @PutMapping("detail/{estateId}")
    public ResponseEntity<EstateResponseDto> enrollTanent(@PathVariable Long estateId, @RequestBody EstateUpdateRequestDto estateUpdateRequestDto
    ) {
        EstateResponseDto estateResponseDto = estateService.updateEstate(estateId,estateUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }


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
