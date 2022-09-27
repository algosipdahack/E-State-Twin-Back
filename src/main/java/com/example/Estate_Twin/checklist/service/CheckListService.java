package com.example.Estate_Twin.checklist.service;

import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.web.dto.*;
import com.example.Estate_Twin.media.domain.entity.Media;

import java.util.List;

public interface CheckListService {
    CheckListResponseDto getCheckList(Long id);
    CheckListResponseDto saveCheckList(CheckListSaveRequestDto checkListSaveRequestDto, Long assetId);
    CheckListResponseDto updateCheckList(Long id, CheckListUpdateRequestDto checkListUpdateRequestDto);
    List<CheckListResponseDto> getAllCheckListByAssetId(Long assetId);
}
