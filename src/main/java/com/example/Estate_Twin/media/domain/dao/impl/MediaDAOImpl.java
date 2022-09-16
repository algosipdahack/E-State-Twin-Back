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
    public Media saveMedia(Media media) {
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

    @Override
    public Media updateEstate(Long id, Estate estate) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미디어가 없습니다. id = " + id));
        media.setEstate(estate);
        return media;
    }

    @Override
    public Media updateAsset(Long id, Asset asset) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미디어가 없습니다. id = " + id));
        media.setAsset(asset);
        return media;
    }

    @Override
    public Media updateCheckList(Long id, CheckList checkList) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미디어가 없습니다. id = " + id));
        media.setCheckList(checkList);
        return media;
    }
}
