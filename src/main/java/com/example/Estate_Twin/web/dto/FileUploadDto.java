package com.example.Estate_Twin.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "s3 업로드할 이미지/동영상 모델",description = "매물의 이미지/동영상을 나타내는 모델")
public class FileUploadDto {
    @ApiModelProperty(value = "매물 이미지/동영상 경로")
    @JsonProperty
    private String FileUrl;
}
