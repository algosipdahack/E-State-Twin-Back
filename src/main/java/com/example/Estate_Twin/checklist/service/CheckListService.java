package com.example.Estate_Twin.checklist.service;

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
    public CheckListResponseDto findById(Long id) {
        CheckList checkList = checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
        return new CheckListResponseDto(checkList);
    }

    @Transactional
    public Long save(CheckListSaveRequestDto checkListSaveRequestDto) {
        return checkListRepository.save(checkListSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, CheckListUpdateRequestDto checkListUpdateRequestDto) {
        CheckList checkList = checkListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 체크리스트가 없습니다. id = "+id));
        checkList.update(checkListUpdateRequestDto.getCheckListContent(),
                checkListUpdateRequestDto.getBrokerConfirmYN(),checkListUpdateRequestDto.getOwnerConfirmYN(),
                checkListUpdateRequestDto.getCheckListContent(),checkListUpdateRequestDto.getCheckListContent(),
                checkListUpdateRequestDto.getRepairDate(),checkListUpdateRequestDto.getRepairType(),
                checkListUpdateRequestDto.getCheckListContent(),checkListUpdateRequestDto.getAsset());
        return id;
    }
}
