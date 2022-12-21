package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.address.*;
import com.example.Estate_Twin.asset.data.dao.impl.AssetDAOImpl;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.contractstate.domain.dao.impl.ContractStateDAOImpl;
import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.estate.domain.dao.EstateHitDAO;
import com.example.Estate_Twin.estate.domain.dao.impl.*;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.repository.EstateHitRepository;
import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.domain.dao.impl.HouseDAOImpl;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.user.domain.dao.impl.*;
import com.example.Estate_Twin.user.domain.entity.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EstateServiceImpl implements EstateService {
    private final EstateDAOImpl estateDAO;
    private final HouseDAOImpl houseDAO;
    private final BrokerDAOImpl brokerDAO;
    private final AssetDAOImpl assetDAO;
    private final ContractStateDAOImpl contractStateDAO;
    private final PreferEstateDAOImpl preferEstateDAO;
    private final EstateHitDAO estateHitDAO;
    private final UserDAOImpl userDAO;


    @Override
    public Long saveFirst(Address address, Long brokerId, Long ownerId) {
        //owner 매핑, estate 생성, broker한테 알림
        return estateDAO.saveFirst(brokerDAO.findBrokerById(brokerId), userDAO.findUserById(ownerId), address).getId();
    }


    //조회수 증가시키기 + 최근 본 매물에 포함
    @Override
    public EstateDetailDto getEstate(Long estateId, User user) {
        // Estate에 Lock을 걸어버림
        Estate estate = estateDAO.getEstate(estateId);
        // 최근 본 매물에 포함
        preferEstateDAO.savePreferEstate(estate, user, Preference.RECENT);

        EstateDetailDto detail = new EstateDetailDto(estate, houseDAO.findHouseByEstateId(estateId),
                estateDAO.findBrokerbyEstateId(estateId),estateHitDAO.getEstateHit(estateId), assetDAO.findAssetsByEstateId(estateId), user);

        // 사용자가 문의했는지 확인 -> arCam 활성화
        detail.setInquiry(preferEstateDAO.existPreferEstate(estate.getId(), user.getId(), Preference.INQUIRY));
        return detail;
    }

    @Override
    // state, estatehit
    public EstateResponseDto saveEstate(EstateSaveRequestDto estateSaveRequestDto) {
        // 기본 정보 저장
        Estate estate = estateDAO.findEstate(estateSaveRequestDto.getId());

        // asset 정보 저장
        List<Asset> assets = new ArrayList<>();
        estateSaveRequestDto.getAssets().forEach(asset -> {
            assets.add(assetDAO.saveAsset(estate, asset.toEntity()));
        });

        //house 정보 저장
        House house = houseDAO.saveHouse(estateSaveRequestDto.getHouse().toEntity());

        estate.detailUpdate(estateSaveRequestDto);

        contractStateDAO.updateState(estate,State.POST_DOING);

        return new EstateResponseDto(estateDAO.saveEstate(estate,house), house, estateHitDAO.getEstateHit(estate.getId()), assets);
    }

    @Override
    public Page<EstateListResponseDto> getAllEstate(Long estateId, Pageable pageable) {
        return estateDAO.findAllEstateList(estateId, pageable);
    }

    @Override
    public EstateResponseDto updateEstate(Long estateId, EstateUpdateRequestDto estateUpdateRequestDto) {
        //house 값 수정
        House house = houseDAO.updateHouse(estateDAO.findHouse(estateId), estateUpdateRequestDto.getHouse());

        //estate 값 수정
        return new EstateResponseDto(estateDAO.updateEstate(estateId, estateUpdateRequestDto), house, estateHitDAO.getEstateHit(estateId), assetDAO.findAssetsByEstateId(estateId));
    }

    @Override
    public List<EstateMainDto> getEstateCustomized(User user) {
        //유저가 선호하는 지역 보여줌
        String borough = user.getBorough();
        return estateDAO.findEstateCustomized(borough);
    }

    @Override
    public EstateResponseDto allowPost(Long estateId, User user) {
        Estate estate = estateDAO.findEstate(estateId);
        Estate newEstate;

        // 유저 role 검증
        if (user.isBroker()) { // Broker라면
            Broker broker = brokerDAO.findBrokerByEmail(user.getEmail());
            newEstate = estateDAO.allowBroker(estate, broker);
        } else { // 집주인이라면
            newEstate = estateDAO.allowOwner(estate, user);
        }

        if (estateDAO.checkEnroll(newEstate)) {
            contractStateDAO.updateState(newEstate, State.POST_DONE);
        }

        return new EstateResponseDto(newEstate, houseDAO.findHouseByEstateId(estateId), estateHitDAO.getEstateHit(estateId), assetDAO.findAssetsByEstateId(estateId));
    }

    @Override
    @Transactional
    public ContractStateResponseDto startContract(Long estateId, User user) {
        Estate estate = estateDAO.matchTenant(estateId, user);
        ContractState contractState = contractStateDAO.updateState(estate,State.CONTRACT_REQUEST);
        return new ContractStateResponseDto(contractState);
    }

    // 사용자 최근 검색 변화
    @Override
    public List<EstateListResponseDto> searchEstate(User user, AddressSearchDto addressSearchDto, Pageable pageable) {
        estateDAO.updateBorough(userDAO.findUserById(user.getId()), addressSearchDto.getBorough());
        if (addressSearchDto.getTown() != null) {
            return estateDAO.findEstateListByTown(addressSearchDto.getTown(), pageable);
        }
        return estateDAO.findEstateListByBorough(addressSearchDto.getBorough(), pageable);
    }
}
