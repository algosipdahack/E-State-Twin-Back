package com.example.Estate_Twin.checklist.web;

import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.checklist.web.dto.*;
import com.example.Estate_Twin.media.service.AwsS3Service;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "CheckList", description = "체크리스트 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estate/asset/checklist")
public class CheckListApiController {
    private final CheckListService checkListService;
    private final AwsS3Service awsS3Service;
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
    @PostMapping("/{assetId}")
    public ResponseEntity<CheckListResponseDto> saveCheckList(@PathVariable Long assetId, @RequestBody CheckListSaveRequestDto checkListSaveRequestDto) {
        CheckListResponseDto checkListResponseDto = checkListService.saveCheckList(checkListSaveRequestDto,assetId);
        if (checkListSaveRequestDto.getCheckListPhotos() != null) {
            awsS3Service.uploadCheckList(checkListSaveRequestDto.getCheckListPhotos(), checkListResponseDto.getId(), "checklist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDto);
    }

    @Operation(summary = "put checklist", description = "체크리스트 수정하기")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CheckListResponseDto.class)))})
    @Parameters({@Parameter(name = "checklistId", description = "Checklist Id", example = "1")})
    @PutMapping("/{checklistId}")
    public ResponseEntity<CheckListResponseDto> updateCheckList(@PathVariable Long checklistId, @RequestBody CheckListUpdateRequestDto checkListUpdateRequestDto){
        CheckListResponseDto checkListResponseDto = checkListService.updateCheckList(checklistId,checkListUpdateRequestDto);
        awsS3Service.uploadCheckList(checkListUpdateRequestDto.getCheckListPhotos(),checkListResponseDto.getId(),"checklist");
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDto);
    }
}
