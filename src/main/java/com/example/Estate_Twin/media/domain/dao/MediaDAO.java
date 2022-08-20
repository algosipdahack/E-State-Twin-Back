package com.example.Estate_Twin.media.domain.dao;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.media.domain.entity.Media;


public interface MediaDAO {
    Media saveMedia(Media media);
    Media findMedia(Long id);
    Media updateMedia(Long id, String origFileName, String filepath);
    Media updateEstate(Long id, Estate estate);
    Media updateAsset(Long id, Asset asset);
    Media updateCheckList(Long id, CheckList checkList);
}
