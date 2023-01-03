package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.address.*;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.data.repository.AssetRepository;
import com.example.Estate_Twin.contractstate.domain.dao.impl.ContractStateDAOImpl;
import com.example.Estate_Twin.contractstate.domain.entity.*;
import com.example.Estate_Twin.contractstate.web.dto.ContractStateResponseDto;
import com.example.Estate_Twin.estate.domain.dao.EstateHitDAO;
import com.example.Estate_Twin.estate.domain.dao.impl.*;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.domain.repository.EstateRepository;
import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.domain.repository.HouseRepository;
import com.example.Estate_Twin.user.domain.entity.*;
import com.example.Estate_Twin.user.domain.repository.BrokerRepository;
import com.example.Estate_Twin.user.domain.repository.UserRepository;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EstateServiceImpl implements EstateService {
    private final EstateDAOImpl estateDAO;
    private final ContractStateDAOImpl contractStateDAO;
    private final PreferEstateDAOImpl preferEstateDAO;
    private final EstateHitDAO estateHitDAO;
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final BrokerRepository brokerRepository;
    private final AssetRepository assetRepository;
    private final EstateRepository estateRepository;

    @Override
    public Long saveFirst(Address address, Long brokerId, Long ownerId) {
        //owner 매핑, estate 생성, broker한테 알림
        return estateDAO.saveFirst(brokerRepository.findById(brokerId).orElseThrow(()-> new CheckHouseException(ErrorCode.BROKER_NOT_FOUND)), userRepository.findById(ownerId).orElseThrow(() -> new CheckHouseException(ErrorCode.USER_NOT_FOUND)), address).getId();
    }


    //조회수 증가시키기 + 최근 본 매물에 포함
    @Override
    public EstateDetailDto getEstate(Long estateId, User user) {
        // Estate에 Lock을 걸어버림
        Estate estate = estateDAO.getEstate(estateId);
        // 최근 본 매물에 포함
        preferEstateDAO.savePreferEstate(estate, user, Preference.RECENT);

        EstateDetailDto detail = new EstateDetailDto(estate, houseRepository.findHouseByEstate_Id(estateId).orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND)),
                estateDAO.findBrokerbyEstateId(estateId),estateHitDAO.getEstateHit(estateId), assetRepository.findAssetsByEstate_Id(estateId).orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND)), user);

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
        estateSaveRequestDto.getAssets().forEach(dto -> {
            Asset asset = dto.toEntity();
            asset.setEstate(estate);

            assets.add(assetRepository.save(asset));
        });

        //house 정보 저장
        House house = houseRepository.save(estateSaveRequestDto.getHouse().toEntity());

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
        House house = estateRepository.findHouseByEstateId(estateId).update(estateUpdateRequestDto.getHouse());

        //estate 값 수정
        return new EstateResponseDto(estateDAO.updateEstate(estateId, estateUpdateRequestDto), house, estateHitDAO.getEstateHit(estateId), assetRepository.findAssetsByEstate_Id(estateId).orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND)));
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
            Broker broker = brokerRepository.findByUserEmailWithUserUsingFetchJoin(user.getEmail()).orElseThrow(()-> new CheckHouseException(ErrorCode.USER_NOT_FOUND));
            newEstate = estateDAO.allowBroker(estate, broker);
        } else { // 집주인이라면
            newEstate = estateDAO.allowOwner(estate, user);
        }

        if (estateDAO.checkEnroll(newEstate)) {
            contractStateDAO.updateState(newEstate, State.POST_DONE);
        }

        return new EstateResponseDto(newEstate, houseRepository.findHouseByEstate_Id(estateId).orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND)), estateHitDAO.getEstateHit(estateId), assetRepository.findAssetsByEstate_Id(estateId).orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND)));
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
        estateDAO.updateBorough(userRepository.findById(user.getId()).orElseThrow(() -> new CheckHouseException(ErrorCode.USER_NOT_FOUND)), addressSearchDto.getBorough());
        if (addressSearchDto.getTown() != null) {
            return estateDAO.findEstateListByTown(addressSearchDto.getTown(), pageable);
        }
        return estateDAO.findEstateListByBorough(addressSearchDto.getBorough(), pageable);
    }
}
