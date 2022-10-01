package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.data.dao.impl.AssetDAOImpl;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.estate.domain.dao.impl.EstateDAOImpl;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.exception.Exception;
import com.example.Estate_Twin.house.domain.dao.impl.HouseDAOImpl;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import com.example.Estate_Twin.user.domain.dao.impl.*;
import com.example.Estate_Twin.user.domain.entity.*;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EstateServiceImpl implements EstateService {
    private final EstateDAOImpl estateDAO;
    private final HouseDAOImpl houseDAO;
    private final UserDAOImpl userDAO;
    private final BrokerDAOImpl brokerDAO;
    private final AssetDAOImpl assetDAO;

    @Override
    public Long saveFirst(Address address, Long brokerId, String email) {
        //owner 매핑, estate 생성, broker한테 알림
        return estateDAO.saveFirst(brokerDAO.findBrokerById(brokerId), userDAO.findUserByEmail(email) ,address).getId();
    }

    //조회수 증가시키기
    @Override
    public EstateResponseDto getEstate(Long id) {
        return new EstateResponseDto(estateDAO.getEstate(id));
    }

    @Override
    @Transactional
    // state, estatehit
    public EstateResponseDto saveEstate(EstateSaveRequestDto estateSaveRequestDto) {
        // 기본 정보 저장
        Estate estate = estateDAO.findEstate(estateSaveRequestDto.getId());

        // asset 정보 저장
        List<Asset> assets = new ArrayList<>();
        estateSaveRequestDto.getAssets().forEach(asset -> {
            assets.add(assetDAO.saveAsset(estate,asset.toEntity()));
        });

        //house 정보 저장
        House house = houseDAO.saveHouse(estateSaveRequestDto.getHouse().toEntity());
        estate.detailUpdate(estateSaveRequestDto, assets, house);
        return new EstateResponseDto(estateDAO.saveEstate(estate));
    }

    @Override
    public List<EstateListResponseDto> getAllEstate() {
        return estateDAO.findAllEstateList();
    }

    @Override
    public EstateResponseDto updateEstate(Long id, EstateUpdateRequestDto estateUpdateRequestDto) {
        House house = estateDAO.findHouse(id);
        houseDAO.updateHouse(house, estateUpdateRequestDto.getHouse());

        return new EstateResponseDto(estateDAO.updateEstate(id, estateUpdateRequestDto));
    }

    @Override
    public List<EstateMainDto> getEstateCustomized(String email) {
        //유저가 선호하는 지역 보여줌
        String borough = userDAO.findUserByEmail(email).getBorough();
        return estateDAO.findEstateCustomized(borough);
    }

    @Override
    public EstateResponseDto allowPost(Long estateId, String email) {
        User user = userDAO.findUserByEmail(email);
        Estate estate = estateDAO.findEstate(estateId);
        // 유저 role 검증
        if (user.isBroker()) { // Broker라면
            Broker broker = brokerDAO.findBrokerByEmail(email);
            return new EstateResponseDto(estateDAO.allowBroker(estate, broker));
        } else { // 집주인이라면
            return new EstateResponseDto(estateDAO.allowOwner(estate, user));
        }
    }


}
