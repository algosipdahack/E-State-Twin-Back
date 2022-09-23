package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.address.data.dao.AddressDAO;
import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.address.web.dto.AddressUpdateRequestDto;
import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.dao.EstateHitDAO;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.exception.Exception;
import com.example.Estate_Twin.house.domain.dao.HouseDAO;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.media.service.AwsS3Service;
import com.example.Estate_Twin.user.domain.dao.BrokerDAO;
import com.example.Estate_Twin.user.domain.dao.UserDAO;
import com.example.Estate_Twin.user.domain.entity.Broker;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EstateServiceImpl implements EstateService {
    private final EstateDAO estateDAO;
    private final HouseDAO houseDAO;
    private final AddressDAO addressDAO;
    private final UserDAO userDAO;
    private final BrokerDAO brokerDAO;
    private final AssetDAO assetDAO;
    private final EstateHitDAO estateHitDAO;
    private final AwsS3Service awsS3Service;
    @Override
    public EstateResponseDto getEstate(Long id) {
        Estate estate = estateDAO.findEstate(id);
        estateHitDAO.updateHit(estate);
        EstateResponseDto estateResponseDto = new EstateResponseDto(estateDAO.findEstate(id));
        estateResponseDto.setNull(estateDAO.findEstateHit(id),estateDAO.findAssets(id),estateDAO.findAddress(id),estateDAO.findHouse(id));
        return estateResponseDto;
    }

    @Override
    public EstateResponseDto saveEstate(EstateSaveRequestDto estateSaveRequestDto) {
        Address address = addressDAO.saveAddress(estateSaveRequestDto.getAddress().toEntity());
        List<Asset> assets = new ArrayList<>();
        estateSaveRequestDto.getAssetSaveRequestDtos().forEach(assetSaveRequestDto -> {
            Asset asset = assetDAO.saveAsset(assetSaveRequestDto.toEntity());
            awsS3Service.uploadAsset(assetSaveRequestDto.getAssetPhotos(),asset.getId(),"asset");
            assets.add(asset);
        });
        House house = houseDAO.saveHouse(estateSaveRequestDto.getHouse().toEntity());
        return new EstateResponseDto(estateDAO.saveEstate(estateSaveRequestDto.toEntity(),house,address,assets));
    }

    @Override
    public Estate addMedia(Long id, Media media) {
        return estateDAO.addEstateMedia(id, media);
    }

    @Override
    public void clearMedia(Long id) {
        estateDAO.clearMedia(id);
    }

    @Override
    public List<EstateListResponseDto> getAllEstate() {
        return estateDAO.findAllEstateList();
    }

    @Override
    public EstateResponseDto updateEstate(Long id, EstateUpdateRequestDto estateUpdateRequestDto) {
        Estate estate = estateDAO.findEstate(id);
        Long addressId = estate.getAddress().getId();
        Long houseId = estate.getHouse().getId();

        AddressUpdateRequestDto address = estateUpdateRequestDto.getAddress();
        addressDAO.updateAddress(addressId, address.getCity(), address.getBorough(),
                address.getTown(),address.getComplexName(), address.getBlock(),
                address.getUnit(), address.getRoadName(), address.getMainBuildingNumber(),
                address.getSubBuildingNumber(), address.getBuildingName());

        HouseUpdateRequestDto house = estateUpdateRequestDto.getHouse();
        houseDAO.updateHouse(houseId,house.getDeposit(),house.getMonthlyRent(),house.getSellingFee(),
                house.getCurrentFloors(),house.getTotalFloors(),house.isShortTermRent(),house.getMaintenanceFee(),
                house.getItemsIncludedMaintenanceFee(),house.getNetRentableArea(),house.getRentableArea(),
                house.isParking(),house.getParkingFee(),house.getMoveInAvailableDate(),house.getSize(),house.getHeatType(),
                house.getEstateType(),house.getHousehold(),house.getRoomCount(),house.getUsageAvailableDate(),house.getBathCount());

        return new EstateResponseDto(estateDAO.updateEstate(id, estateUpdateRequestDto.getContent(), estateUpdateRequestDto.getModel(),
                TransactionType.of(estateUpdateRequestDto.getTransactionType()), estateUpdateRequestDto.getEstateThumbNail(),
                address.getCity(), address.getBorough(), address.getTown(),
                estateUpdateRequestDto.getThumbNail3D()));
    }

    @Override
    public List<EstateMainDto> getEstateCustomized(String email) {
        String borough = userDAO.findUserByEmail(email).getBorough();
        return estateDAO.findEstateCustomized(borough);
    }

    //아예 owner랑 broker가 맞다는 가정 하에 진행
    @Override
    public EstateResponseDto allowPost(Long estateId, String email) {
        User user = userDAO.findUserByEmail(email);
        Estate estate = estateDAO.findEstate(estateId);
        EstateResponseDto estateResponseDto = new EstateResponseDto(estate);

        // 유저 role 검증
        if (user.isBroker()) { // Broker라면
            Broker broker = brokerDAO.findBrokerByEmail(email);
            if (checkRoleBroker(estateId, broker) == null) {
                throw new Exception("해당 매물의 broker가 아닙니다!");
            }
            estateResponseDto.setBrokerConfirmYN(true);
            estateDAO.allowBroker(estate);
        } else { // 집주인이라면
            if (checkRoleOwner(estateId, user) == null) {
                throw new Exception("해당 매물의 owner가 아닙니다!");
            }
            estateResponseDto.setOwnerConfirmYN(true);
            estateDAO.allowOwner(estate);
        }
        if(checkEnroll(estate)) {
            estateResponseDto.setPosted(true);
        }
        return estateResponseDto;
    }

    // 해당 매물이 올릴 수 있는 상태인지 확인
    @Override
    public boolean checkEnroll(Estate estate) {
        if (estate.isOwnerConfirmYN() && estate.isBrokerConfirmYN()) {
            estateDAO.enablePost(estate);
            return true;
        }
        return false;
    }

    // 해당 매물의 브로커인지 확인
    @Override
    public Estate checkRoleBroker(Long estateId, Broker broker) {
        return broker.getTradeEstates().stream().filter(estate -> estate.getId().equals(estateId))
                        .findAny().orElse(null);
    }
    //해당 매물의 소유주인지 확인
    @Override
    public Estate checkRoleOwner(Long estateId, User owner) {
        return owner.getOwnEstates().stream().filter(estate -> estate.getId().equals(estateId))
                .findAny().orElse(null);
    }
}
