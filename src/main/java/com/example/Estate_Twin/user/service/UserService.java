package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.auth.jwt.Token;
import com.example.Estate_Twin.estate.web.dto.EstateModeDto;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.web.dto.*;

import java.util.List;

public interface UserService {
    UserResponseDto getUserById(Long id);
    UserInfoDto signUp(User user, UserSignUpDto userSignUpDto);
    Long deleteUser(User user);
    List<AssetResponseDto> getTenantAsset(Long userId, Category category);
    EstateModeDto getTenantAssetList(Long userId);
    List<EstateModeDto> getOwnerAssetList(Long userId);
    List<AssetResponseDto> getOwnerAsset(Long userId, Long estateId);
    Token login(String providerName, String accessToken);
}
