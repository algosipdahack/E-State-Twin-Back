package com.example.Estate_Twin.house.web;

import com.example.Estate_Twin.house.domain.House;
import com.example.Estate_Twin.house.service.HouseService;
import com.example.Estate_Twin.house.web.dto.HouseResponseDto;
import com.example.Estate_Twin.house.web.dto.HouseSaveRequestDto;
import com.example.Estate_Twin.house.web.dto.HouseUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/house")
public class HouseApiController {
    private final HouseService houseService;

    @GetMapping("/{houseId}")
    public HouseResponseDto getHouse(@PathVariable Long houseId) {
        return houseService.findById(houseId);
    }

    @PostMapping("")
    public Long saveHouse(@RequestBody HouseSaveRequestDto houseSaveRequestDto) {
        return houseService.save(houseSaveRequestDto);
    }

    @PutMapping("/{houseId}")
    public Long updateHouse(@PathVariable Long houseId, @RequestBody HouseUpdateRequestDto houseUpdateRequestDto){
        return houseService.update(houseId, houseUpdateRequestDto);
    }

}
