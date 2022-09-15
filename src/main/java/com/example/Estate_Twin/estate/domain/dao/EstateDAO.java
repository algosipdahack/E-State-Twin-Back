package com.example.Estate_Twin.estate.domain.dao;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.media.domain.entity.Media;

import java.util.List;

public interface EstateDAO {
    Estate saveEstate(Estate estate, House house, Address address, List<Asset> assets);
    Estate findEstate(Long id);
    Estate updateEstate(Long id, String content, String model,
                        TransactionType transactionType, String estateThumbNail,
                        String city, String borough, String thumbnail3D);
    Estate addEstateMedia(Long id, Media media);
    List<Estate> findEstateCustomized(String borough);
    List<Estate> findAllEstate();
    void clearMedia(Estate estate);
    Estate allowBroker(Estate estate);
    Estate allowOwner(Estate estate);
}
