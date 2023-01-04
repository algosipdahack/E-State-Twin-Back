package com.example.Estate_Twin.user.service.impl;

import com.example.Estate_Twin.asset.data.entity.Category;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.auth.jwt.Token;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.web.dto.EstateModeDto;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.domain.repository.UserRepository;
import com.example.Estate_Twin.user.service.UserService;
import com.example.Estate_Twin.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private AssetRepository assetRepository;
    private EstateRepository estateRepository;
    private final OAuthService oAuthService;
    private final InMemoryClientRegistrationRepository inMemoryRepository;

    @Transactional
    public Token login(String providerName, String accessToken) {
        ClientRegistration provider = inMemoryRepository.findByRegistrationId(providerName);
        // kakao로부터 유저정보 받아서 db에 저장
        return oAuthService.getUserProfile(providerName.toUpperCase(), accessToken, provider);
    }

    @Override
    public UserInfoDto signUp(User user, UserSignUpDto userSignUpDto) {
        return new UserInfoDto(userRepository.save(user.signup(userSignUpDto)));
    }

    @Override
    public Long deleteUser(User user) {
        return userRepository.save(user.delUser()).getId();
    }

    @Override
    public List<AssetResponseDto> getTenantAsset(Long userId, Category category) {
        return assetRepository.findTenantAsset(userId, category);
    }

    @Override
    public List<AssetResponseDto> getOwnerAsset(Long userId, Long estateId) {
        return assetRepository.findOwnerAsset(userId, estateId);
    }

    @Override
    public EstateModeDto getTenantAssetList(Long userId) {
        return estateRepository.findTenantEstateList(userId);
    }

    @Override
    public List<EstateModeDto> getOwnerAssetList(Long userId) {
        return estateRepository.findOwnerEstateList(userId);
    }

}

