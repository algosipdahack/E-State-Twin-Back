package com.example.Estate_Twin.media.web.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class MediaSaveMultipartRequestDto {

    private MultipartFile imageFile;
}
