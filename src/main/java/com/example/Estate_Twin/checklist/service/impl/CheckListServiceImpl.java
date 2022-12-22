package com.example.Estate_Twin.checklist.service.impl;

import com.example.Estate_Twin.asset.data.dao.impl.AssetDAOImpl;
import com.example.Estate_Twin.checklist.data.dao.impl.CheckListDAOImpl;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.checklist.service.CheckListService;
import com.example.Estate_Twin.checklist.web.dto.*;
import com.example.Estate_Twin.estate.domain.dao.impl.EstateDAOImpl;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.user.domain.entity.User;
import io.swagger.models.auth.In;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
@Log4j2
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
        try {
            return new CheckListResponseDto(checkListDAO.confirm(checklistId, user));
        } catch (ObjectOptimisticLockingFailureException e) {
            return new CheckListResponseDto(checkListDAO.confirm(checklistId, user));
        }
    }
}
