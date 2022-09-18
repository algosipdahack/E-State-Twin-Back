package com.example.Estate_Twin.media.web.dto;

import com.example.Estate_Twin.media.domain.entity.Media;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MediaResponseDto {
    private final Long id;
    private final String origFileName;
    private final String filePath;
    @QueryProjection
    public MediaResponseDto(Media media) {
        this.id = media.getId();
        this.origFileName = media.getOrigFileName();
        this.filePath = media.getFilePath();
    }
    public Media toEntity() {
        return Media.builder()
                .origFileName(origFileName)
                .filePath(filePath)
                .build();
    }

}
