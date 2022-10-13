package com.example.Estate_Twin.checklist.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CheckListUpdateRequestDto {
    private String flawPart;
    private String checkListContent;
    @Schema(description = "구매/수리", example = "PURCHASE, REPAIR")
    private String repairType;
    private String manufacturer;
    private String checkListPhoto;
    private Boolean brokerConfirmYN;
    private Boolean ownerConfirmYN;
    private Boolean tenentConfirmYN;
    private LocalDateTime repairDate;
}
