package com.example.Estate_Twin.checklist.web;

import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.checklist.web.dto.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/checklist")
public class CheckListApiController {
    private final CheckListService checkListService;


    @GetMapping("/{checklistId}")
    public ResponseEntity<CheckListResponseDto> getCheckList(@PathVariable Long checklistId) {
        CheckListResponseDto checkListResponseDto = checkListService.getCheckList(checklistId);
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDto);
    }

    @PostMapping("/{assetId}")
    public ResponseEntity<CheckListResponseDto> saveCheckList(@PathVariable Long assetId, @RequestBody CheckListSaveRequestDto checkListSaveRequestDto) {
        CheckListResponseDto checkListResponseDto = checkListService.saveCheckList(checkListSaveRequestDto,assetId);
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDto);
    }

    @PutMapping("/{checklistId}")
    public ResponseEntity<CheckListResponseDto> updateCheckList(@PathVariable Long checklistId, @RequestBody CheckListUpdateRequestDto checkListUpdateRequestDto){
        CheckListResponseDto checkListResponseDto = checkListService.updateCheckList(checklistId,checkListUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(checkListResponseDto);
    }
}
