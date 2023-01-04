package com.example.Estate_Twin.checklist.service.impl;

import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.checklist.data.dao.impl.CheckListDAOImpl;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.data.repository.CheckListRepository;
import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.checklist.web.dto.*;
import com.example.Estate_Twin.estate.domain.dao.impl.EstateDAOImpl;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CheckListServiceImpl implements CheckListService {
    private final CheckListDAOImpl checkListDAO;
    private final EstateDAOImpl estateDAO;
    private final AssetRepository assetRepository;
    private final CheckListRepository checkListRepository;

    @Override
    public CheckListResponseDto getCheckList(Long checkListId) {
        return new CheckListResponseDto(checkListRepository.findById(checkListId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.CHECKLIST_NOT_FOUND)));
    }

    @Override
    public CheckListResponseDto saveCheckList(User user, CheckListSaveRequestDto checkListSaveRequestDto, Long estateId, Long assetId) {
        return new CheckListResponseDto(checkListDAO.saveCheckList(user, checkListSaveRequestDto.toEntity(), estateDAO.getEstate(estateId), assetRepository.findByIdUsingFetchJoinCheckList(assetId)
                .orElseThrow(()-> new CheckHouseException(ErrorCode.ASSET_NOT_FOUND))));
    }

    @Override
    public CheckListResponseDto updateCheckList(Long checkListId, CheckListUpdateRequestDto dto) {
        CheckList checkList = checkListRepository.findById(checkListId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.CHECKLIST_NOT_FOUND));
        checkList.update(dto);
        return new CheckListResponseDto(checkList);
    }

    @Override
    public List<CheckListResponseDto> getAllCheckListByAssetId(Long assetId) {
        List<CheckListResponseDto> dtos = new ArrayList<>();
        checkListRepository.findCheckListsByAsset_IdOrderByRepairDateDesc(assetId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.ASSET_NOT_FOUND)).forEach(checkList -> dtos.add(new CheckListResponseDto(checkList)));
        return dtos;
    }

    @Override
    // 상태 -> CheckList_Doing -> contract_done
    public CheckListResponseDto confirmCheckList(Long checklistId, User user) {
        try {
            return new CheckListResponseDto(checkListDAO.confirm(checklistId, user));
        } catch (ObjectOptimisticLockingFailureException e) {
            return new CheckListResponseDto(checkListDAO.confirm(checklistId, user));
        }
    }
}
