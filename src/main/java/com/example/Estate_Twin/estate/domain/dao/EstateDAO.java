package com.example.Estate_Twin.estate.domain.dao;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.address.web.dto.AddressDto;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.asset.web.dto.AssetResponseDto;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.estate.web.dto.EstateHitDto;
import com.example.Estate_Twin.estate.web.dto.EstateListResponseDto;
import com.example.Estate_Twin.estate.web.dto.EstateMainDto;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.web.dto.HouseDto;
import com.example.Estate_Twin.media.domain.entity.Media;

import java.util.List;

public interface EstateDAO {
    Estate saveEstate(Estate estate, House house, Address address, List<Asset> assets);
    Estate findEstate(Long id);
    Estate updateEstate(Long id, String content, String model,
                        TransactionType transactionType, String estateThumbNail,
                        String city, String borough, String town, String thumbnail3D);
    Estate addEstateMedia(Long id, Media media);
    List<EstateMainDto> findEstateCustomized(String borough);
    List<EstateListResponseDto> findAllEstateList();
    void clearMedia(Long estateId);
    Estate allowBroker(Estate estate);
    Estate allowOwner(Estate estate);
    AddressDto findAddress(Long id);
    HouseDto findHouse(Long id);
    EstateHitDto findEstateHit(Long id);
    List<AssetResponseDto> findAssets(Long id);
    Estate enablePost(Estate estate);

}
