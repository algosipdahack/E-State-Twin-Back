package com.example.Estate_Twin.media.web.dto;

import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.Getter;

@Getter
public class MediaResponseDto {
    private final String origFileName;
    private final String filePath;

    public MediaResponseDto(Media media) {
        this.origFileName = media.getOrigFileName();
        this.filePath = media.getFilePath();
    }


}
