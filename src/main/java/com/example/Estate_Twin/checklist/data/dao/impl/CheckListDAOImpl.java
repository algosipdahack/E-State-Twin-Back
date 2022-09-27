package com.example.Estate_Twin.checklist.data.dao.impl;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.web.dto.AssetDto;
import com.example.Estate_Twin.checklist.data.dao.CheckListDAO;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.checklist.data.repository.CheckListRepository;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Component
public class CheckListDAOImpl implements CheckListDAO {
    private CheckListRepository checkListRepository;

    @Override
    public CheckList saveCheckList(CheckList checkList, Asset asset) {
        checkList.setAsset(asset);
        return checkListRepository.save(checkList);
    }

    @Override
    public CheckList findCheckList(Long id) {
        return checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
    }


    @Override
    public List<CheckListResponseDto> findAllCheckList(Long assetId) {
        return checkListRepository.findCheckListbyAssetId(assetId);
    }

    @Override
    public CheckList updateCheckList(Long id, String flawPart, Boolean brokerConfirmYN, Boolean ownerConfirmYN, String checkListContent, LocalDateTime repairDate, RepairType repairType) {
        CheckList newCheckList = checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id))
                .builder()
                .flawPart(flawPart)
                .brokerConfirmYN(brokerConfirmYN)
                .ownerConfirmYN(ownerConfirmYN)
                .checkListContent(checkListContent)
                .repairDate(repairDate)
                .repairType(repairType)
                .build();
        return checkListRepository.save(newCheckList);
    }

}
