package com.example.Estate_Twin.checklist.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckListUpdateRequestDto {
    @NotNull
    private String flawPart;
    @NotNull
    private String checkListContent;
    @NotNull
    @Schema(description = "구매/수리", example = "PURCHASE, REPAIR")
    private String repairType;
    @NotNull
    private String checkListPhoto;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate repairDate;
}
