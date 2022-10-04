package com.example.Estate_Twin.checklist.service.impl;

import com.example.Estate_Twin.asset.data.dao.impl.AssetDAOImpl;
import com.example.Estate_Twin.checklist.data.dao.impl.CheckListDAOImpl;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.checklist.web.dto.*;
import com.example.Estate_Twin.contractstate.domain.dao.impl.ContractStateDAOImpl;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.dao.impl.EstateDAOImpl;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.user.domain.dao.impl.UserDAOImpl;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CheckListServiceImpl implements CheckListService {
    private final CheckListDAOImpl checkListDAO;
    private final AssetDAOImpl assetDAO;
    private final UserDAOImpl userDAO;
    private final EstateDAOImpl estateDAO;
    private final ContractStateDAOImpl contractStateDAO;

    @Override
    public CheckListResponseDto getCheckList(Long id) {
        return new CheckListResponseDto(checkListDAO.findCheckList(id));
    }

    @Override
    public CheckListResponseDto saveCheckList(CheckListSaveRequestDto checkListSaveRequestDto, Long assetId) {
        return new CheckListResponseDto(checkListDAO.saveCheckList(checkListSaveRequestDto.toEntity(),assetDAO.findAsset(assetId)));
    }

    @Override
    public CheckListResponseDto updateCheckList(Long id, CheckListUpdateRequestDto dto) {
        return new CheckListResponseDto(checkListDAO.updateCheckList(id, dto));
    }

    @Override
    public List<CheckListResponseDto> getAllCheckListByAssetId(Long assetId) {
        List<CheckListResponseDto> dtos = new ArrayList<>();
        checkListDAO.findAllCheckList(assetId).forEach(checkList -> new CheckListResponseDto(checkList));
        return dtos;
    }

    @Override
    // 상태 -> CheckList_Doing -> contract_done
    public CheckListResponseDto confirmCheckList(Long estateId, Long checklistId, String email) {
        User user = userDAO.findUserByEmail(email);
        CheckList checkList = checkListDAO.findCheckList(checklistId);
        Estate estate = estateDAO.findEstate(estateId);
        CheckList newCheckList;
        // 유저 role 검증
        if (user.isBroker()) { // Broker라면
            newCheckList = checkListDAO.confirmBroker(checkList);
        } else { // 집주인 or 세입자라면
            newCheckList = checkListDAO.confirmUser(estateId, checkList, user);
        }
        //체크리스트 등록 끝났다면 계약 완료 상태
        if (checkListDAO.checkDone(newCheckList)) {
            contractStateDAO.updateState(estate, State.CONTRACT_DONE);
        }
        return new CheckListResponseDto(newCheckList);
    }
}
