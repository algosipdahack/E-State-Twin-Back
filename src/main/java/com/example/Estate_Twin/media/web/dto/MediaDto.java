package com.example.Estate_Twin.media.web.dto;

import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.Getter;

@Getter
public class MediaDto {
    private final Long id;
    private final String origFileName;
    private final String filePath;

    public MediaDto(Media media) {
        this.id = media.getId();
        this.origFileName = media.getOrigFileName();
        this.filePath = media.getFilePath();
    }
}
