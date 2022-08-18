package com.example.Estate_Twin.house.web;

import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.house.service.HouseService;
import com.example.Estate_Twin.house.web.dto.HouseResponseDto;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/house")
public class HouseApiController {
    private final HouseService houseService;

    @GetMapping("/{houseId}")
    public ResponseEntity<HouseResponseDto> getHouse(@PathVariable Long houseId) {
        HouseResponseDto houseResponseDto = houseService.getHouse(houseId);
        return ResponseEntity.status(HttpStatus.OK).body(houseResponseDto);
    }

    @PostMapping("")
    public ResponseEntity<HouseResponseDto> saveHouse(@RequestBody HouseSaveRequestDto houseSaveRequestDto) {
        HouseResponseDto houseResponseDto = houseService.saveHouse(houseSaveRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(houseResponseDto);
    }

    @PutMapping("/{houseId}")
    public ResponseEntity<HouseResponseDto> updateHouse(@PathVariable Long houseId, @RequestBody HouseUpdateRequestDto houseUpdateRequestDto){
        HouseResponseDto houseResponseDto = houseService.updateHouse(houseId, houseUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(houseResponseDto);
    }

}
