package com.example.Estate_Twin.media.web.dto;

import com.example.Estate_Twin.media.domain.entity.Media;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MediaDto {
    private final String origFileName;
    private final String filePath;
    @QueryProjection
    public MediaDto(Media media) {
        this.origFileName = media.getOrigFileName();
        this.filePath = media.getFilePath();
    }
    public MediaDto(String origFileName, String filePath) {
        this.origFileName = origFileName;
        this.filePath = filePath;
    }
}
