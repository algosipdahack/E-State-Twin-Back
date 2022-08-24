package com.example.Estate_Twin.checklist.service.impl;

import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.checklist.data.dao.CheckListDAO;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListSaveRequestDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CheckListServiceImpl implements CheckListService {

    private final CheckListDAO checkListDAO;
    private final AssetDAO assetDAO;

    @Override
    public CheckListResponseDto getCheckList(Long id) {
        return new CheckListResponseDto(checkListDAO.findCheckList(id));
    }

    @Override
    public CheckListResponseDto saveCheckList(CheckListSaveRequestDto checkListSaveRequestDto, Long assetId) {
        return new CheckListResponseDto(checkListDAO.saveCheckList(checkListSaveRequestDto.toEntity(),assetDAO.findAsset(assetId)));
    }

    @Override
    public CheckListResponseDto updateCheckList(Long id, CheckListUpdateRequestDto checkListUpdateRequestDto) {
        CheckList checkList = checkListDAO.updateCheckList(id,checkListUpdateRequestDto.getFlawPart(),checkListUpdateRequestDto.getBrokerConfirmYN(),
                checkListUpdateRequestDto.getOwnerConfirmYN(),checkListUpdateRequestDto.getCategory(),checkListUpdateRequestDto.getCheckListContent(),
                checkListUpdateRequestDto.getRepairDate(),checkListUpdateRequestDto.getRepairType(),checkListUpdateRequestDto.getManufacturer());
        return new CheckListResponseDto(checkList);
    }

    @Override
    public CheckList addMedia(Long checklistId, List<Media> mediaList) {
        return checkListDAO.addCheckListMedia(checklistId,mediaList);
    }
}
