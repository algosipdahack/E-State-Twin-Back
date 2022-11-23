package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.checklist.data.dao.impl.CheckListDAOImpl;
import com.example.Estate_Twin.estate.web.dto.EstateModeDto;
import com.example.Estate_Twin.estate.web.dto.EstateOwnerDto;
import com.example.Estate_Twin.user.domain.dao.impl.UserDAOImpl;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.service.UserService;
import com.example.Estate_Twin.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAOImpl userDAO;
    private final CheckListDAOImpl checkListDAO;
    @Override
    public UserResponseDto getUserbyId(Long id) {
        return new UserResponseDto(userDAO.findUserById(id));
    }

    public UserInfoDto getUser(User user) {
        return new UserInfoDto(user);
    }

    @Override
    public UserInfoDto signUp(User user, UserSignUpDto userSignUpDto) {
        return new UserInfoDto(userDAO.signUp(user, userSignUpDto));
    }

    @Override
    public Long deleteUser(User user) {
        return userDAO.deleteMember(user).getId();
    }

    @Override
    public List<AssetResponseDto> getTenantAsset(Long userId, Category category) {
        List<AssetResponseDto> assets = new ArrayList<>();
        userDAO.getTenantAsset(userId, category).forEach(asset -> assets.add(new AssetResponseDto(asset, checkListDAO.findCheckListsByAssetId(asset.getId()))));
        return assets;
    }

    @Override
    public List<AssetResponseDto> getOwnerAsset(Long userId, Long estateId) {
        List<AssetResponseDto> assets = new ArrayList<>();
        userDAO.getOwnerAsset(userId, estateId).forEach(asset -> assets.add(new AssetResponseDto(asset, checkListDAO.findCheckListsByAssetId(asset.getId()))));
        return assets;
    }

    @Override
    public EstateModeDto getTenantAssetList(Long userId) {
        return userDAO.getTenantAssetList(userId);
    }

    @Override
    public List<EstateOwnerDto> getOwnerAssetList(Long userId) {
        return userDAO.getOwnerAssetList(userId);
    }

}

