package com.example.Estate_Twin.checklist.service;

import com.example.Estate_Twin.checklist.web.dto.*;
import com.example.Estate_Twin.user.domain.entity.User;

import java.util.List;

public interface CheckListService {
    CheckListResponseDto getCheckList(Long id);
    CheckListResponseDto saveCheckList(User user, CheckListSaveRequestDto checkListSaveRequestDto, Long estateId, Long assetId);
    CheckListResponseDto updateCheckList(Long id, CheckListUpdateRequestDto checkListUpdateRequestDto);
    CheckListResponseDto confirmCheckList(Long estateId, Long checklistId, User user);
    List<CheckListResponseDto> getAllCheckListByAssetId(Long assetId);
}
