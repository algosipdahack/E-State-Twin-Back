package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.estate.web.dto.EstateResponseDto;
import com.example.Estate_Twin.estate.web.dto.EstateSaveRequestDto;
import com.example.Estate_Twin.estate.web.dto.EstateUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/estate/")
public class EstateApiController {
    private final EstateService estateService;

    //Estate posting upload
    //@PostMapping("")
    //public Long saveEstate(@RequestBody EstateSaveRequestDto requestDto) {
        //return estateService.save(requestDto);
    //}

    //리스트
    //@GetMapping("list")

    //상세 페이지
    @GetMapping("detail/{estateId}")
    public EstateResponseDto getDetail(@PathVariable Long id) {
        return estateService.findById(id);
    }

    @PostMapping("detail")
    public Long saveEstate(@RequestBody EstateSaveRequestDto estateSaveRequestDto) {
        return estateService.save(estateSaveRequestDto);
    }

    @PutMapping("detail/{estateId}")
    public Long updateEstate(@PathVariable Long id, @RequestBody EstateUpdateRequestDto estateUpdateRequestDto) {
        return estateService.update(id, estateUpdateRequestDto);

    }
    //매물 영상
    //@GetMapping("/detail/{estateId}/video")

    //3D model
    //@GetMapping("/detail/{estateId}/model")

}
