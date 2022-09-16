package com.example.Estate_Twin.estate.service.impl;

import com.example.Estate_Twin.address.data.dao.AddressDAO;
import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.address.web.dto.AddressUpdateRequestDto;
import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.estate.domain.dao.EstateHitDAO;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.domain.dao.HouseDAO;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.media.service.AwsS3Service;
import com.example.Estate_Twin.user.domain.dao.UserDAO;
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
    private final AssetDAO assetDAO;
    private final EstateHitDAO estateHitDAO;
    private final AwsS3Service awsS3Service;
    @Override
    public EstateResponseDto getEstate(Long id) {
        Estate estate = estateDAO.findEstate(id);
        estateHitDAO.updateHit(estate);
        return new EstateResponseDto(estateDAO.findEstate(id));
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
        List<Estate> estates = estateDAO.findAllEstate();
        List<EstateListResponseDto> estateListResponseDtos = new ArrayList<>();
        estates.forEach(estate -> estateListResponseDtos.add(new EstateListResponseDto(estate)));
        return estateListResponseDtos;
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
                estateUpdateRequestDto.getTransactionType(), estateUpdateRequestDto.getEstateThumbNail(),
                estateUpdateRequestDto.getCity(), estateUpdateRequestDto.getBorough(),
                estateUpdateRequestDto.getThumbNail3D()));
    }

    @Override
    public List<EstateListResponseDto> getEstateCustomized(String borough) {
        List<EstateListResponseDto> estateListResponseDtos = new ArrayList<>();
        estateDAO.findEstateCustomized(borough).forEach(estate -> {
            estateListResponseDtos.add(new EstateListResponseDto(estate));
        });
        return estateListResponseDtos;
    }

    @Override
    public EstateResponseDto allowPost(Long estateId, Long userId) {
        User user = userDAO.findUser(userId);
        //유저 role 검증
        //broker라면
        if (user.isBroker()) {
            //TODO 진짜 매물의 broker인지 확인

            return new EstateResponseDto(estateDAO.allowBroker(estateDAO.findEstate(estateId)));
        } else{
            return new EstateResponseDto(estateDAO.allowOwner(estateDAO.findEstate(estateId)));
        }
    }
}
