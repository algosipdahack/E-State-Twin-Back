package com.example.Estate_Twin.checklist.service;

import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.asset.domain.repository.AssetRepository;
import com.example.Estate_Twin.checklist.domain.CheckList;
import com.example.Estate_Twin.checklist.domain.repository.CheckListRepository;
import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListSaveRequestDto;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CheckListService {
    private final CheckListRepository checkListRepository;
    private final AssetRepository assetRepository;

    public CheckListResponseDto findById(Long id) {
        CheckList checkList = checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
        return new CheckListResponseDto(checkList);
    }

    @Transactional
    public Long save(CheckListSaveRequestDto checkListSaveRequestDto, Long assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(()->new IllegalArgumentException("해당 에셋이 없습니다. id = "+assetId));
        checkListSaveRequestDto.setAsset(asset);
        return checkListRepository.save(checkListSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, CheckListUpdateRequestDto checkListUpdateRequestDto) {
        CheckList checkList = checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
        checkList.update(checkListUpdateRequestDto.getFlawPart(), checkListUpdateRequestDto.getBrokerConfirmYN(), checkListUpdateRequestDto.getOwnerConfirmYN().booleanValue(),
        checkListUpdateRequestDto.getCategory(), checkListUpdateRequestDto.getCheckListContent(), checkListUpdateRequestDto.getRepairDate(),
        checkListUpdateRequestDto.getRepairType(), checkListUpdateRequestDto.getManufacturer());
        return id;
    }
}
