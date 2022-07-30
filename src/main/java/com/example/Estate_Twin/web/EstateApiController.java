package com.example.Estate_Twin.web;

import com.example.Estate_Twin.service.Estate.EstateService;
import com.example.Estate_Twin.web.dto.ApiResult;
import com.example.Estate_Twin.web.dto.EstateResponseDto;
import com.example.Estate_Twin.web.dto.EstateSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/estate/")
public class EstateApiController {
    private final EstateService estateService;

    //Estate posting upload
    @PostMapping("")
    public Long saveEstate(@RequestBody EstateSaveRequestDto requestDto) {
        return estateService.save(requestDto);
    }

    //리스트
    //@GetMapping("list")

    //상세 페이지
    //@GetMapping("detail/{estateId}")

    //매물 영상
    //@GetMapping("/detail/{estateId}/video")

    //3D model
    //@GetMapping("/detail/{estateId}/model")

}
