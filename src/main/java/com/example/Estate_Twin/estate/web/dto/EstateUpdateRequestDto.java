package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.address.web.dto.AddressUpdateRequestDto;
import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.estate.domain.entity.TransactionType;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import com.example.Estate_Twin.media.web.dto.MediaSaveMultipartRequestDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class EstateUpdateRequestDto {
    private TransactionType transactionType;
    private String estateThumbNail;
    private String content;
    private String thumbNail3D;
    private String model;
    private String city;
    private String borough;
    private String town;
    private List<MediaSaveMultipartRequestDto> estatePhotos;
    private AddressUpdateRequestDto address;
    private HouseUpdateRequestDto house;

    @Builder
    public EstateUpdateRequestDto(String transactionType, HouseUpdateRequestDto house,
                                  String estateThumbNail, String content, String model,
                                  String thumbNail3D, AddressUpdateRequestDto address,
                                  List<MediaSaveMultipartRequestDto> estatePhotos) {
        this.transactionType = TransactionType.of(transactionType);
        this.house = house;
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.model = model;
        this.thumbNail3D = thumbNail3D;
        this.address = address;
        this.city = address.getCity();
        this.borough = address.getBorough();
        this.town = address.getTown();
        this.estatePhotos = new ArrayList<>();
        estatePhotos.forEach(photo -> this.estatePhotos.add(photo));
    }
}
