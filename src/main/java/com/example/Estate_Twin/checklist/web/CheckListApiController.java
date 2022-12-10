package com.example.Estate_Twin.checklist.web;

import com.example.Estate_Twin.checklist.service.impl.CheckListServiceImpl;
import com.example.Estate_Twin.checklist.web.dto.*;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.util.CurrentUser;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "CheckList", description = "체크리스트 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/checklist")
public class CheckListApiController {
    private final CheckListServiceImpl checkListService;

    @Operation(summary = "get checklists by asset", description = "에셋에 대한 체크리스트 정보들 가져오기")
    @ApiResponses(value = { @ApiResponse(content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CheckListResponseDto.class)))})})
    @Parameter(name = "assetId", description = "Asset Id", example = "1")
    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<CheckListResponseDto>> getCheckListbyAsset(@PathVariable Long assetId) {
        List<CheckListResponseDto> checkListResponseDtos = checkListService.getAllCheckListByAssetId(assetId);
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDtos);
    }

    @Operation(summary = "get checklist", description = "체크리스트에 대한 정보들 가져오기")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CheckListResponseDto.class)))
    @Parameter(name = "checklistId", description = "Checklist Id", example = "1")
    @GetMapping("/{checklistId}")
    public ResponseEntity<CheckListResponseDto> getCheckList(@PathVariable Long checklistId) {
        CheckListResponseDto checkListResponseDto = checkListService.getCheckList(checklistId);
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDto);
    }

    // 만약 집주인이 등록 -> 세입자는 없는 상태이므로 tenantY 까지 해주기
    // 세입자가 등록 -> 세입자만 tenantY해주기
    @Operation(summary = "post checklist", description = "체크리스트 등록하기")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = CheckListResponseDto.class)))
    @Parameter(name = "assetId", description = "Asset Id", example = "1")
    @PostMapping("/estate/{estateId}/asset/{assetId}")
    public ResponseEntity<CheckListResponseDto> saveCheckList(@Parameter(hidden = true) @CurrentUser User user,
                                                              @PathVariable Long estateId,
                                                              @PathVariable Long assetId,
                                                              @RequestBody @Valid CheckListSaveRequestDto checkListSaveRequestDto) {
        CheckListResponseDto checkListResponseDto = checkListService.saveCheckList(user,checkListSaveRequestDto,estateId,assetId);
        return ResponseEntity.status(HttpStatus.CREATED).body(checkListResponseDto);
    }

    @Operation(summary = "put checklist", description = "체크리스트 수정하기")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CheckListResponseDto.class)))
    @Parameter(name = "checklistId", description = "Checklist Id", example = "1")
    @PutMapping("/{checklistId}")
    public ResponseEntity<CheckListResponseDto> updateCheckList(@PathVariable Long checklistId, @RequestBody CheckListUpdateRequestDto checkListUpdateRequestDto){
        CheckListResponseDto checkListResponseDto = checkListService.updateCheckList(checklistId,checkListUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDto);
    }

    @Operation(summary = "Confirm of checklist post", description = "중개인/집주인의 체크리스트 승인")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CheckListResponseDto.class)))
    @Parameter(name = "estateId", description = "Estate Id", example = "1")
    @PatchMapping("/{checklistId}/estate/{estateId}/confirm")
    public ResponseEntity<CheckListResponseDto> confirmCheckList(@Parameter(hidden = true) @CurrentUser User user,
                                                                 @PathVariable Long checklistId,
                                                                 @PathVariable Long estateId) {
        CheckListResponseDto checkListResponseDto = checkListService.confirmCheckList(estateId, checklistId, user);
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDto);
    }

}
