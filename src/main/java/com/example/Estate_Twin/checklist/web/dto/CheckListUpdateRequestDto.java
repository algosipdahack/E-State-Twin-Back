package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class CheckListUpdateRequestDto {
    private String flawPart;
    private String checkListContent;
    @Schema(description = "구매/수리", example = "PURCHASE, REPAIR")
    private String repairType;
    private String manufacturer;
    private Boolean brokerConfirmYN;
    private Boolean ownerConfirmYN;
    private Boolean tanentConfirmYN;
    private LocalDateTime repairDate;
    private List<MediaSaveMultipartRequestDto> checkListPhotos;
}
