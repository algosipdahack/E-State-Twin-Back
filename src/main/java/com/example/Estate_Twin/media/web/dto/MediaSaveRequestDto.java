package com.example.Estate_Twin.media.web.dto;

import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class MediaSaveRequestDto {
    private String origFileName;
    private String filePath;

    @Builder
    public MediaSaveRequestDto(String origFileName, String filePath) {
        this.origFileName = origFileName;
        this.filePath = filePath;
    }

    public Media toEntity() {
        return Media.builder()
                .origFileName(origFileName)
                .filePath(filePath)
                .build();
    }
}
