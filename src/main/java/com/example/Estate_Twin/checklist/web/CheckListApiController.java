package com.example.Estate_Twin.checklist.web;

import com.example.Estate_Twin.checklist.service.impl.CheckListServiceImpl;
import com.example.Estate_Twin.checklist.web.dto.*;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import com.example.Estate_Twin.user.domain.entity.CustomUserDetails;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CheckList", description = "체크리스트 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/checklist")
public class CheckListApiController {
    private final CheckListServiceImpl checkListService;

    @Operation(summary = "get checklists by asset", description = "에셋에 대한 체크리스트 정보들 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CheckListResponseDto.class)))})
    @Parameters({@Parameter(name = "assetId", description = "Asset Id", example = "1")})
    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<CheckListResponseDto>> getCheckListbyAsset(@PathVariable Long assetId) {
        List<CheckListResponseDto> checkListResponseDtos = checkListService.getAllCheckListByAssetId(assetId);
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDtos);
    }

    @Operation(summary = "get checklist", description = "체크리스트에 대한 정보들 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CheckListResponseDto.class)))})
    @Parameters({@Parameter(name = "checklistId", description = "Checklist Id", example = "1")})
    @GetMapping("/{checklistId}")
    public ResponseEntity<CheckListResponseDto> getCheckList(@PathVariable Long checklistId) {
        CheckListResponseDto checkListResponseDto = checkListService.getCheckList(checklistId);
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDto);
    }

    @Operation(summary = "post checklist", description = "체크리스트 등록하기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CheckListResponseDto.class)))})
    @Parameters({@Parameter(name = "assetId", description = "Asset Id", example = "1")})
    @PostMapping("/asset/{assetId}")
    public ResponseEntity<CheckListResponseDto> saveCheckList(@PathVariable Long assetId, @RequestBody CheckListSaveRequestDto checkListSaveRequestDto) {
        CheckListResponseDto checkListResponseDto = checkListService.saveCheckList(checkListSaveRequestDto,assetId);
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDto);
    }

    @Operation(summary = "put checklist", description = "체크리스트 수정하기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CheckListResponseDto.class)))})
    @Parameters({@Parameter(name = "checklistId", description = "Checklist Id", example = "1")})
    @PutMapping("/{checklistId}")
    public ResponseEntity<CheckListResponseDto> updateCheckList(@PathVariable Long checklistId, @RequestBody CheckListUpdateRequestDto checkListUpdateRequestDto){
        CheckListResponseDto checkListResponseDto = checkListService.updateCheckList(checklistId,checkListUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDto);
    }
/*
    // TODO 브로커, 집주인 승인
    @Operation(summary = "Confirm of checklist post", description = "중개인/집주인의 체크리스트 승인")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstateResponseDto.class)))})
    @Parameters({@Parameter(name = "estateId", description = "Estate Id", example = "1")})
    @PutMapping("/{checklistId}/confirm")
    public ResponseEntity<CheckListResponseDto> confirmCheckList(@PathVariable Long checklistId, @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails user) {
        CheckListResponseDto checkListResponseDto = checkListService.confirmCheckList(checklistId, user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDto);
    }*/
}
