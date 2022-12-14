package com.example.Estate_Twin.checklist.data.dao;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.user.domain.entity.User;

import java.util.List;

public interface CheckListDAO {
    CheckList saveCheckList(User user, CheckList checkList, Estate estate, Asset asset);
    CheckList confirm(Long checkListId, User user);
}
