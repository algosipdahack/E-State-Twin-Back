package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.checklist.data.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CheckListSaveRequestDto {
    private String flawPart;
    private String checkListContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate repairDate;
    @Schema(description = "구매/수리", example = "PURCHASE, REPAIR")
    private String repairType;
    private String checkListPhoto;

    public CheckList toEntity() {
        return CheckList.builder()
                .checkListContent(checkListContent)
                .flawPart(flawPart)
                .repairDate(repairDate)
                .checkListPhoto(checkListPhoto)
                .repairType(RepairType.of(repairType))
                .build();
    }

}
