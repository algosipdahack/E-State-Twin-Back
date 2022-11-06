package com.example.Estate_Twin.checklist.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Boolean tenantConfirmYN;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime repairDate;
}
