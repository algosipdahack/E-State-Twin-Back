package com.example.Estate_Twin.estate.domain.dao;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.user.domain.entity.*;
import org.springframework.data.domain.*;

import java.util.List;

public interface EstateDAO {
    Estate saveEstate(Estate estate, House house);
    Estate findEstate(Long id);
    List<Estate> findEstatesByBrokerId(Long brokerId);
    List<Estate> findEstatesByOwnerId(Long ownerId);
    Estate findEstateByHouseId(Long houseId);
    Estate findEstateByEstateHitId(Long estatehitId);
    Estate updateEstate(Long id, EstateUpdateRequestDto estateUpdateRequestDto);
    List<EstateMainDto> findEstateCustomized(String borough);
    Page<EstateListResponseDto> findAllEstateList(Long estateId, Pageable pageable);
    Estate allowBroker(Estate estate, Broker broker);
    Estate allowOwner(Estate estate, User owner);
    Estate getEstate(Long id);
    House findHouse(Long id);
    EstateDetailDto getEstateDetail(Long estateId);
    EstateHit findEstateHit(Long id);
    List<AssetResponseDto> findAssets(Long id);
    boolean checkEnroll(Estate estate);
    Estate saveFirst(Broker broker, User owner, Address address);
    Estate matchTenant(Long estateId, User user);
    List<EstateListResponseDto> findEstateListByBorough(String borough, Pageable pageable);
    List<EstateListResponseDto> findEstateListByTown(String town, Pageable pageable);
    User updateBorough(User user, String region);

}
