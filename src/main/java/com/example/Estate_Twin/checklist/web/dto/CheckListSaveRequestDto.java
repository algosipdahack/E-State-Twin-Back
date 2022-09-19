package com.example.Estate_Twin.checklist.web.dto;

import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CheckListSaveRequestDto {
    private String flawPart;
    private String checkListContent;
    private LocalDateTime repairDate;
    private RepairType repairType;
    private Boolean brokerConfirmYN;
    private Boolean ownerConfirmYN;
    private List<MediaSaveMultipartRequestDto> checkListPhotos;

    @Builder
    public CheckListSaveRequestDto(String flawPart, String checkListContent, LocalDateTime repairDate,
                                   String repairType, List<MediaSaveMultipartRequestDto> checkListPhotos,
                                   Boolean brokerConfirmYN, Boolean ownerConfirmYN) {
        this.flawPart = flawPart;
        this.checkListContent = checkListContent;
        this.repairDate = repairDate;
        this.repairType = RepairType.of(repairType);
        this.brokerConfirmYN = brokerConfirmYN;
        this.ownerConfirmYN = ownerConfirmYN;
        this.checkListPhotos = new ArrayList<>();
        checkListPhotos.forEach(checkListPhoto -> this.checkListPhotos.add(checkListPhoto));
    }

    public CheckList toEntity() {
        return CheckList.builder()
                .checkListContent(checkListContent)
                .ownerConfirmYN(ownerConfirmYN)
                .brokerConfirmYN(brokerConfirmYN)
                .flawPart(flawPart)
                .repairDate(repairDate)
                .repairType(repairType)
                .build();
    }

}
