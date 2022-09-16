package com.example.Estate_Twin.media.domain.dao.impl;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.media.domain.dao.MediaDAO;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.media.domain.repository.MediaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MediaDAOImpl implements MediaDAO {
    private MediaRepository mediaRepository;

    @Override
    public Media saveEstateMedia(Estate estate, Media media) {
        media.setEstate(estate);
        return mediaRepository.save(media);
    }
    @Override
    public Media saveAssetMedia(Asset asset, Media media) {
        media.setAsset(asset);
        return mediaRepository.save(media);
    }
    @Override
    public Media saveCheckListMedia(CheckList checkList, Media media) {
        media.setCheckList(checkList);
        return mediaRepository.save(media);
    }

    @Override
    public Media findMedia(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 미디어가 없습니다. id = "+id));
    }

    @Override
    public Media updateMedia(Long id, String origFileName, String filepath) {
        Media newMedia = mediaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 미디어가 없습니다. id = "+id))
                .builder()
                .origFileName(origFileName)
                .filePath(filepath)
                .build();
        return mediaRepository.save(newMedia);
    }

}
