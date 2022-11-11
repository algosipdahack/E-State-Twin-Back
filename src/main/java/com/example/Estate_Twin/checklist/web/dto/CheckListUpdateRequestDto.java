package com.example.Estate_Twin.checklist.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CheckListUpdateRequestDto {
    private String flawPart;
    private String checkListContent;
    @Schema(description = "구매/수리", example = "PURCHASE, REPAIR")
    private String repairType;
    private String manufacturer;
    private String checkListPhoto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate repairDate;
}
