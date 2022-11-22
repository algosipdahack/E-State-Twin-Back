package com.example.Estate_Twin.checklist.service.impl;

import com.example.Estate_Twin.asset.data.dao.impl.AssetDAOImpl;
import com.example.Estate_Twin.checklist.data.dao.impl.CheckListDAOImpl;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.checklist.web.dto.*;
import com.example.Estate_Twin.estate.domain.dao.impl.EstateDAOImpl;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CheckListServiceImpl implements CheckListService {
    private final CheckListDAOImpl checkListDAO;
    private final AssetDAOImpl assetDAO;
    private final EstateDAOImpl estateDAO;

    @Override
    public CheckListResponseDto getCheckList(Long id) {
        return new CheckListResponseDto(checkListDAO.findCheckList(id));
    }

    @Override
    public CheckListResponseDto saveCheckList(User user, CheckListSaveRequestDto checkListSaveRequestDto, Long estateId, Long assetId) {
        return new CheckListResponseDto(checkListDAO.saveCheckList(user, checkListSaveRequestDto.toEntity(), estateDAO.getEstate(estateId), assetDAO.findAsset(assetId)));
    }

    @Override
    public CheckListResponseDto updateCheckList(Long id, CheckListUpdateRequestDto dto) {
        return new CheckListResponseDto(checkListDAO.updateCheckList(id, dto));
    }

    @Override
    public List<CheckListResponseDto> getAllCheckListByAssetId(Long assetId) {
        List<CheckListResponseDto> dtos = new ArrayList<>();
        checkListDAO.findCheckListsByAssetId(assetId).forEach(checkList -> dtos.add(new CheckListResponseDto(checkList)));
        return dtos;
    }

    @Override
    // 상태 -> CheckList_Doing -> contract_done
    public CheckListResponseDto confirmCheckList(Long estateId, Long checklistId, User user) {
        CheckList checkList = checkListDAO.findCheckList(checklistId);

        CheckList newCheckList;
        // 유저 role 검증
        if (user.isBroker()) // Broker라면
        {
            newCheckList = checkListDAO.confirmBroker(checkList);
        }
        else // 집주인이라면
        {
            newCheckList = checkListDAO.confirmOwner(checkList);
        }

        //체크리스트 등록 끝 -> totalConfirmY
        if (checkListDAO.checkDone(newCheckList)) {
            newCheckList = checkListDAO.confirmTotal(checkList);
        }
        return new CheckListResponseDto(newCheckList);
    }
}
