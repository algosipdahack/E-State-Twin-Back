package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.checklist.data.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckListSaveRequestDto {
    @NotNull
    private String flawPart;
    @NotNull
    private String checkListContent;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate repairDate;
    @NotNull
    @Schema(description = "구매/수리", example = "PURCHASE, REPAIR")
    private String repairType;
    @NotNull
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
