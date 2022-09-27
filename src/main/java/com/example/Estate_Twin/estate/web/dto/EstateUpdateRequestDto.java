package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressUpdateRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
public class EstateUpdateRequestDto {
    private String transactionType;
    private String estateThumbNail;
    private String content;
    private String thumbNail3D;
    private String model;
    private List<String> estatePhotos;
    private AddressUpdateRequestDto address;
    private HouseUpdateRequestDto house;
}
