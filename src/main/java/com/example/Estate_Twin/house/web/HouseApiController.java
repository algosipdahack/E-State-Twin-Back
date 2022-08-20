package com.example.Estate_Twin.house.web;

import com.example.Estate_Twin.house.service.HouseService;
import com.example.Estate_Twin.house.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/house")
public class HouseApiController {
    private final HouseService houseService;

    @GetMapping("/{houseId}")
    public ResponseEntity<HouseResponseDto> getHouse(@PathVariable Long houseId) {
        return ResponseEntity.status(HttpStatus.OK).body(houseService.getHouse(houseId));
    }

    @PostMapping("")
    public ResponseEntity<HouseResponseDto> saveHouse(@RequestBody HouseSaveRequestDto houseSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(houseService.saveHouse(houseSaveRequestDto));
    }

    @PutMapping("/{houseId}")
    public ResponseEntity<HouseResponseDto> updateHouse(@PathVariable Long houseId, @RequestBody HouseUpdateRequestDto houseUpdateRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(houseService.updateHouse(houseId, houseUpdateRequestDto));
    }

}
