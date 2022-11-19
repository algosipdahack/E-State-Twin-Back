package com.example.Estate_Twin.user.domain.dao;

import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.estate.web.dto.EstateModeDto;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.web.dto.UserSignUpDto;

import java.util.List;

public interface UserDAO {
    User findUserById(Long id);
    User findUserByEmail(String email);
    User signUp(User user, UserSignUpDto dto);
    User deleteMember(User user);
    List<Asset> getTenantAsset(Long userId, Category category);
    EstateModeDto getTenantAssetList(Long userId);
    List<EstateModeDto> getOwnerAssetList(Long userId);
    List<Asset> getOwnerAsset(Long userId, Long estateId);
    User findUserByTenantEstateId(Long tenantId);
}
