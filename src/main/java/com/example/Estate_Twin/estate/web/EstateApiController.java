package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estate/")
public class EstateApiController {
    private final EstateService estateService;

    //Estate posting upload
    //@PostMapping("")
    //public Long saveEstate(@RequestBody EstateSaveRequestDto requestDto) {
        //return estateService.save(requestDto);
    //}

    //리스트
    /*@GetMapping("list")
    public EstateListResponseDto getList(@PathVariable Long id) {
        return estateService.findById(id);
    }*/

    //상세 페이지
    @GetMapping("detail/{estateId}")
    public EstateResponseDto getDetail(@PathVariable Long estateId) {
        return estateService.findById(estateId);
    }

    @PostMapping("detail/{houseId}")
    public Long saveEstate(@PathVariable Long houseId, @RequestBody EstateSaveRequestDto estateSaveRequestDto) {
        return estateService.save(estateSaveRequestDto, houseId);
    }

    @PutMapping("detail/{estateId}")
    public Long updateEstate(@PathVariable Long estateId, @RequestBody EstateUpdateRequestDto estateUpdateRequestDto
    ) {
        return estateService.update(estateId, estateUpdateRequestDto);

    }
    //매물 영상
    //@GetMapping("/detail/{estateId}/video")

    //3D model
    //@GetMapping("/detail/{estateId}/model")

}
