package com.example.Estate_Twin.media.domain.dao.impl;

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
}
