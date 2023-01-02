package com.example.Estate_Twin.user.domain.dao.impl;

import com.example.Estate_Twin.asset.data.entity.*;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.web.dto.EstateModeDto;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.user.domain.dao.UserDAO;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.user.domain.repository.UserRepository;
import com.example.Estate_Twin.user.web.dto.UserSignUpDto;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly = true)
@Component
@AllArgsConstructor
public class UserDAOImpl implements UserDAO {
    private UserRepository userRepository;
    private AssetRepository assetRepository;
    private EstateRepository estateRepository;

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new CheckHouseException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User findUserByTenantEstateId(Long tenantId) {
        return userRepository.findUserByTenantEstate_Id(tenantId).orElseThrow(() -> new CheckHouseException(ErrorCode.USER_NOT_FOUND));
    }
    @Cacheable(value = "user", key = "#email")
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new CheckHouseException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    @Transactional
    public User signUp(User user, UserSignUpDto dto) {
        return userRepository.save(user.signup(dto));
    }

    @Override
    @Transactional
    public User deleteMember(User user) {
        return userRepository.save(user.delUser());
    }

    @Override
    public List<Asset> getTenantAsset(Long userId, Category category) {
        return assetRepository.findTenantAsset(userId, category);
    }

    @Override
    public List<Asset> getOwnerAsset(Long userId, Long estateId) {
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
