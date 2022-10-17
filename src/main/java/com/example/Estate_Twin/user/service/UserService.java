package com.example.Estate_Twin.user.service;

import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.estate.web.dto.EstateModeDto;
import com.example.Estate_Twin.user.web.dto.*;

import java.util.List;

public interface UserService {
    UserResponseDto getUserbyId(Long id);
    UserInfoDto getUserbyEmail(String email);
    UserInfoDto signUp(String email, UserSignUpDto userSignUpDto);
    Long deleteUser(String email);
    List<AssetResponseDto> getTenentAsset(Long userId, Option option);
    EstateModeDto getTenentAssetList(Long userId);
    List<EstateModeDto> getOwnerAssetList(Long userId);
    List<AssetResponseDto> getOwnerAsset(Long userId, Option option);
}
