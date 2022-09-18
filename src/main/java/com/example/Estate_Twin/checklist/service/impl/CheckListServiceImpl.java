package com.example.Estate_Twin.checklist.service.impl;

import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.checklist.data.dao.CheckListDAO;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.checklist.web.dto.*;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckListServiceImpl implements CheckListService {

    private final CheckListDAO checkListDAO;
    private final AssetDAO assetDAO;

    @Override
    public CheckListResponseDto getCheckList(Long id) {
        CheckListResponseDto checkListResponseDto = new CheckListResponseDto(checkListDAO.findCheckList(id));
        checkListResponseDto.setAsset(checkListDAO.findAssetById(id));
        return checkListResponseDto;
    }

    @Override
    public CheckListResponseDto saveCheckList(CheckListSaveRequestDto checkListSaveRequestDto, Long assetId) {
        return new CheckListResponseDto(checkListDAO.saveCheckList(checkListSaveRequestDto.toEntity(),assetDAO.findAsset(assetId)));
    }

    @Override
    public CheckListResponseDto updateCheckList(Long id, CheckListUpdateRequestDto checkListUpdateRequestDto) {
        CheckList checkList = checkListDAO.updateCheckList(id,checkListUpdateRequestDto.getFlawPart(),checkListUpdateRequestDto.getBrokerConfirmYN(),
                checkListUpdateRequestDto.getOwnerConfirmYN(),checkListUpdateRequestDto.getCategory(),checkListUpdateRequestDto.getCheckListContent(),
                checkListUpdateRequestDto.getRepairDate(),checkListUpdateRequestDto.getRepairType());
        return new CheckListResponseDto(checkList);
    }

    @Override
    public CheckList addMedia(Long checklistId, Media media) {
        return checkListDAO.addCheckListMedia(checklistId,media);
    }

    @Override
    public void clearMedia(Long id) {
        checkListDAO.clearMedia(checkListDAO.findCheckList(id));
    }
}
