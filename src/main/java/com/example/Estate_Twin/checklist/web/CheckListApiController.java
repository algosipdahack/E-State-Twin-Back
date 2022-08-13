package com.example.Estate_Twin.checklist.web;

import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListSaveRequestDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/checklist")
public class CheckListApiController {
    private final CheckListService checkListService;

    @GetMapping("/{checklistId}")
    public CheckListResponseDto getCheckList(@PathVariable Long checklistId) {
        return checkListService.findById(checklistId);
    }

    @PostMapping("/{assetId}")
    public Long saveCheckList(@PathVariable Long assetId, @RequestBody CheckListSaveRequestDto checkListSaveRequestDto) {
        return checkListService.save(checkListSaveRequestDto, assetId);
    }

    @PutMapping("/{checklistId}")
    public Long updateCheckList(@PathVariable Long checklistId, @RequestBody CheckListUpdateRequestDto checkListUpdateRequestDto){
        return checkListService.update(checklistId, checkListUpdateRequestDto);
    }
}
