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
public class CheckListUpdateRequestDto {
    private String flawPart;
    private String checkListContent;
    private LocalDateTime repairDate;
    private RepairType repairType;
    private String manufacturer;
    private Boolean brokerConfirmYN;
    private Boolean ownerConfirmYN;
    private List<MediaSaveMultipartRequestDto> checkListPhotos;

    @Builder
    public CheckListUpdateRequestDto(String flawPart, String checkListContent,
                                     LocalDateTime repairDate, RepairType repairType, List<MediaSaveMultipartRequestDto> checkListPhotos,
                                     String manufacturer, Boolean brokerConfirmYN, Boolean ownerConfirmYN) {
        this.flawPart = flawPart;
        this.checkListContent = checkListContent;
        this.repairDate = repairDate;
        this.repairType = repairType;
        this.manufacturer = manufacturer;
        this.brokerConfirmYN = brokerConfirmYN;
        this.ownerConfirmYN = ownerConfirmYN;
        this.checkListPhotos = new ArrayList<>();
        checkListPhotos.forEach(checkListPhoto -> this.checkListPhotos.add(checkListPhoto));
    }
}
