package com.example.Estate_Twin.checklist.service;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.data.repository.CheckListRepository;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListSaveRequestDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface CheckListService {
    CheckListResponseDto getCheckList(Long id);
    Long saveCheckList(CheckListSaveRequestDto checkListSaveRequestDto, Long assetId);
    Long updateCheckList(Long id, CheckListUpdateRequestDto checkListUpdateRequestDto);
}
