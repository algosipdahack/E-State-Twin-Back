package com.example.Estate_Twin.media.web.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class MediaUpdateRequestDto {
    private String origFileName;
    private String filePath;

    @Builder
    public MediaUpdateRequestDto(String origFileName, String filePath) {
        this.origFileName = origFileName;
        this.filePath = filePath;
    }
}
