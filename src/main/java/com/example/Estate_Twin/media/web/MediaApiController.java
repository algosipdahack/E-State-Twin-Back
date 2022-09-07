package com.example.Estate_Twin.media.web;

import com.example.Estate_Twin.checklist.web.dto.CheckListResponseDto;
import com.example.Estate_Twin.media.service.impl.AwsS3ServiceImpl;
import com.example.Estate_Twin.media.web.dto.MediaDto;
import com.example.Estate_Twin.media.web.dto.MediaResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Media", description = "미디어 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/upload")
public class MediaApiController {
    private final AwsS3ServiceImpl awsS3ServiceImpl;

    @Operation(summary = "post Photo of Estate", description = "매물 사진 업로드")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MediaDto.class)))
    })
    @Parameters({
            @Parameter(name = "estateId", description = "Estate Id", example = "1")
    })
    @PostMapping(value = "/estate/{estateId}/photo", consumes = {"multipart/form-data"})
    @ResponseBody()
    //파일 첨부를 안할 수도 있기에 required=false로 설정
    public ResponseEntity<List<MediaDto>> estatePhoto(@RequestPart(required = false) List<MultipartFile> multipartFile, @PathVariable Long estateId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(awsS3ServiceImpl.uploadEstate(multipartFile, estateId, "estate/photo"));
    }

    @Operation(summary = "post Video of Estate", description = "매물 동영상 업로드")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MediaDto.class)))
    })
    @Parameters({
            @Parameter(name = "estateId", description = "Estate Id", example = "1")
    })
    @PostMapping(value = "/estate/{estateId}/video", consumes = {"multipart/form-data"})
    @ResponseBody()
    //파일 첨부를 안할 수도 있기에 required=false로 설정
    public ResponseEntity<List<MediaDto>> estateVideo(@RequestPart(required = false) List<MultipartFile> multipartFile, @PathVariable Long estateId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(awsS3ServiceImpl.uploadEstate(multipartFile, estateId, "estate/video"));
    }

    @Operation(summary = "post Photo of Asset", description = "에셋 사진 업로드")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MediaDto.class)))
    })
    @Parameters({
            @Parameter(name = "assetId", description = "Asset Id", example = "1")
    })
    @PostMapping(value = "/asset/{assetId}", consumes = {"multipart/form-data"})
    @ResponseBody()
    //파일 첨부를 안할 수도 있기에 required=false로 설정
    public ResponseEntity<List<MediaDto>> assetPhoto(@RequestPart(required = false) List<MultipartFile> multipartFile, @PathVariable Long assetId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(awsS3ServiceImpl.uploadAsset(multipartFile, assetId, "asset"));
    }

    @Operation(summary = "post Photo of CheckList", description = "체크리스트 사진 업로드")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MediaDto.class)))
    })
    @Parameters({
            @Parameter(name = "checklistId", description = "CheckList Id", example = "1")
    })
    @PostMapping(value = "/checklist/{checklistId}", consumes = {"multipart/form-data"})
    @ResponseBody()
    //파일 첨부를 안할 수도 있기에 required=false로 설정
    public ResponseEntity<List<MediaDto>> checklistPhoto(@RequestPart(required = false) List<MultipartFile> multipartFile, @PathVariable Long checklistId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(awsS3ServiceImpl.uploadCheckList(multipartFile, checklistId, "checklist"));
    }


    /*@PostMapping(value = "/broker/{brokerId}", consumes = {"multipart/form-data"})
    @ResponseBody()
    //파일 첨부를 안할 수도 있기에 required=false로 설정
    public ResponseEntity<MediaResponseDto> brokerPhoto(@RequestPart(required = false) List<MultipartFile> multipartFile, @PathVariable Long brokerId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(s3Uploader.uploadEstate(multipartFile, brokerId, "idiot/broker"));
    }*/

}