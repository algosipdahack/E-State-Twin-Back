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
import com.example.Estate_Twin.estate.domain.repository.PreferEstateRepository;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

import static javax.management.timer.Timer.ONE_DAY;

@Service
@RequiredArgsConstructor
public class EstateServiceImpl implements EstateService {
    private final EstateDAOImpl estateDAO;
    private final ContractStateDAOImpl contractStateDAO;
    private final EstateHitDAO estateHitDAO;
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final BrokerRepository brokerRepository;
    private final AssetRepository assetRepository;
    private final EstateRepository estateRepository;
    private final PreferEstateRepository preferEstateRepository;

    @Override
    public Long saveFirst(Address address, Long brokerId, Long ownerId) {
        //owner ??????, estate ??????, broker?????? ??????
        return estateDAO.saveFirst(brokerRepository.findById(brokerId).orElseThrow(()-> new CheckHouseException(ErrorCode.BROKER_NOT_FOUND)), userRepository.findById(ownerId).orElseThrow(() -> new CheckHouseException(ErrorCode.USER_NOT_FOUND)), address).getId();
    }


    //????????? ??????????????? + ?????? ??? ????????? ??????
    @Override
    public EstateDetailDto getEstate(Long estateId, User user) {
        // Estate??? Lock??? ????????????
        Estate estate = estateDAO.getEstate(estateId);

        // ?????? ??? ????????? ??????
        preferEstateRepository.save(PreferEstate.builder()
                .preference(Preference.RECENT)
                .estate(estate)
                .user(user)
                .build());

        EstateDetailDto detail = new EstateDetailDto(estate, houseRepository.findHouseByEstate_Id(estateId).orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND)),
                estateRepository.findBrokerByEstate_Id(estateId)
                        .orElseThrow(()->new CheckHouseException(ErrorCode.BROKER_NOT_FOUND)),estateHitDAO.getEstateHit(estateId), assetRepository.findAssetsByEstate_Id(estateId).orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND)), user);

        // ???????????? ??????????????? ?????? -> arCam ?????????
        detail.setInquiry(preferEstateRepository.existsByEstateIdAndUserIdAndPrefer(estate.getId(), user.getId(), Preference.INQUIRY));
        return detail;
    }

    @Override
    // state, estatehit
    public EstateResponseDto saveEstate(EstateSaveRequestDto estateSaveRequestDto) {
        // ?????? ?????? ??????
        Estate estate = estateRepository.findById(estateSaveRequestDto.getId())
                .orElseThrow(()->new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND));

        // asset ?????? ??????
        List<Asset> assets = new ArrayList<>();
        estateSaveRequestDto.getAssets().forEach(dto -> {
            Asset asset = dto.toEntity();
            asset.setEstate(estate);

            assets.add(assetRepository.save(asset));
        });

        //house ?????? ??????
        House house = houseRepository.save(estateSaveRequestDto.getHouse().toEntity());

        estate.detailUpdate(estateSaveRequestDto);

        contractStateDAO.updateState(estate,State.POST_DOING);

        return new EstateResponseDto(estateDAO.saveEstate(estate,house), house, estateHitDAO.getEstateHit(estate.getId()), assets);
    }

    @Override
    public Page<EstateListResponseDto> getAllEstate(Long estateId, Pageable pageable) {
        return estateRepository.findEstateList(estateId, pageable);
    }
    //?????? ?????? ????????? ??????
    @Scheduled(cron = "0 0 0 * * *")
    @CacheEvict(value = "main")
    public void clearCache() {

    }
    @Override
    public EstateResponseDto updateEstate(Long estateId, EstateUpdateRequestDto estateUpdateRequestDto) {
        //house ??? ??????
        House house = estateRepository.findHouseByEstateId(estateId);
        house.update(estateUpdateRequestDto.getHouse());

        //estate ??? ??????
        Estate estate = estateRepository.findById(estateId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND));
        estate.update(estateUpdateRequestDto);
        return new EstateResponseDto(estate, house, estateHitDAO.getEstateHit(estateId), assetRepository.findAssetsByEstate_Id(estateId).orElseThrow(()-> new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND)));
    }

    @Override
    public List<EstateMainDto> getEstateCustomized(User user) {
        //????????? ???????????? ?????? ?????????
        String borough = user.getBorough();
        return estateRepository.findByBoroughOrderByWeeklyHit(borough);
    }

    @Override
    public EstateResponseDto allowPost(Long estateId, User user) {
        Estate estate = estateRepository.findById(estateId)
                .orElseThrow(()->new CheckHouseException(ErrorCode.ESTATE_NOT_FOUND));

        Estate newEstate;

        // ?????? role ??????
        if (user.isBroker()) { // Broker??????
            Broker broker = brokerRepository.findByUserEmailWithUserUsingFetchJoin(user.getEmail()).orElseThrow(()-> new CheckHouseException(ErrorCode.USER_NOT_FOUND));
            newEstate = estateDAO.allowBroker(estate, broker);
        } else { // ??????????????????
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

    // ????????? ?????? ?????? ??????
    @Override
    public List<EstateListResponseDto> searchEstate(User user, String borough, String town, Pageable pageable) {
        // ??????????????? ?????? + ????????? ??? ?????? ??????
        userRepository.findById(user.getId()).orElseThrow(() -> new CheckHouseException(ErrorCode.USER_NOT_FOUND)).setBorough(borough);
        if (town != null) {
            return estateRepository.findEstateByTown(town, pageable);
        }
        return estateRepository.findEstateByBorough(borough, pageable);
    }
}
