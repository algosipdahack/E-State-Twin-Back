package com.example.Estate_Twin.checklist.web;

import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.checklist.web.dto.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CheckList", description = "체크리스트 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/checklist")
public class CheckListApiController {
    private final CheckListService checkListService;

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
}
