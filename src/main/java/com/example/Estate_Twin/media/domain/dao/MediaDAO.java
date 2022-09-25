package com.example.Estate_Twin.media.domain.dao;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.media.domain.entity.Media;


public interface MediaDAO {
    Media saveEstateMedia(Estate estate, Media media);
    Media saveAssetMedia(Asset asset, Media media);
    Media saveCheckListMedia(CheckList checkList, Media media);
    Media findMedia(Long id);
    Media updateMedia(Long id, String origFileName, String filepath);
    Media saveMedia(Media media);
}
