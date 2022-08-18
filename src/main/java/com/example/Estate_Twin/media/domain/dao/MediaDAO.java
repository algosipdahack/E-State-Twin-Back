package com.example.Estate_Twin.media.domain.dao;

import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.media.domain.entity.Media;

import java.time.LocalDateTime;

public interface MediaDAO {
    Media saveMedia(Media media);
    Media findMedia(Long id);
    Media updateMedia(Long id, String origFileName, String filepath);
}
