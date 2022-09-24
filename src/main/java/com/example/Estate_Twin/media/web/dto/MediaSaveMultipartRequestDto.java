package com.example.Estate_Twin.media.web.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@NoArgsConstructor
@Getter
public class MediaSaveMultipartRequestDto {
    private MultipartFile File;
}
