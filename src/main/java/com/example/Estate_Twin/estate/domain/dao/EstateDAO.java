package com.example.Estate_Twin.estate.domain.dao;

import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.media.domain.entity.Media;

import java.util.List;

public interface EstateDAO {
    Estate saveEstate(Estate estate);
    Estate findEstate(Long id);
    Estate updateEstate(Long id, String content, String model, String arCam,
                        ContractState contractState, TransactionType transactionType, String estateThumbNail,
                        String city, String borough, String thumbnail3D);
    Estate addEstateMedia(Long id, List<Media> mediaList);
}
