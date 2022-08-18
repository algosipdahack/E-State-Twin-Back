package com.example.Estate_Twin.checklist.service.impl;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.checklist.data.dao.CheckListDAO;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.data.repository.CheckListRepository;
import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListSaveRequestDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CheckListServiceImpl implements CheckListService {

    private final CheckListDAO checkListDAO;

    @Override
    public CheckListResponseDto getCheckList(Long id) {
        return new CheckListResponseDto(checkListDAO.findCheckList(id));
    }

    @Override
    public Long saveCheckList(CheckListSaveRequestDto checkListSaveRequestDto, Long assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(()->new IllegalArgumentException("해당 에셋이 없습니다. id = "+assetId));
        checkListSaveRequestDto.setAsset(asset);
        return checkListRepository.save(checkListSaveRequestDto.toEntity()).getId();
    }

    @Override
    public Long updateCheckList(Long id, CheckListUpdateRequestDto checkListUpdateRequestDto) {
        CheckList checkList = checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
        checkList.update(checkListUpdateRequestDto.getFlawPart(), checkListUpdateRequestDto.getBrokerConfirmYN(), checkListUpdateRequestDto.getOwnerConfirmYN().booleanValue(),
                checkListUpdateRequestDto.getCategory(), checkListUpdateRequestDto.getCheckListContent(), checkListUpdateRequestDto.getRepairDate(),
                checkListUpdateRequestDto.getRepairType(), checkListUpdateRequestDto.getManufacturer());
        return id;
    }
}
