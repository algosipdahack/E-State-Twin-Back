package com.example.Estate_Twin.estate.web;

import com.example.Estate_Twin.estate.service.EstateService;
import com.example.Estate_Twin.estate.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<EstateResponseDto> getEstate(@PathVariable Long estateId) {
        EstateResponseDto estateResponseDto = estateService.getEstate(estateId);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    @PostMapping("detail/{houseId}")
    public ResponseEntity<EstateResponseDto> saveEstate(@PathVariable Long houseId, @RequestBody EstateSaveRequestDto estateSaveRequestDto) {
        EstateResponseDto estateResponseDto = estateService.saveEstate(estateSaveRequestDto,houseId);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }

    @PutMapping("detail/{estateId}")
    public ResponseEntity<EstateResponseDto> updateEstate(@PathVariable Long estateId, @RequestBody EstateUpdateRequestDto estateUpdateRequestDto
    ) {
        EstateResponseDto estateResponseDto = estateService.updateEstate(estateId,estateUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(estateResponseDto);
    }
    //매물 영상
    //@GetMapping("/detail/{estateId}/video")

    //3D model
    //@GetMapping("/detail/{estateId}/model")

}
