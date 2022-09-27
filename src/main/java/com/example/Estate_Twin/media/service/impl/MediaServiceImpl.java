package com.example.Estate_Twin.media.service.impl;

import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.checklist.data.dao.CheckListDAO;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.media.domain.dao.MediaDAO;
import com.example.Estate_Twin.media.service.MediaService;
import com.example.Estate_Twin.media.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
/*
@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaDAO mediaDAO;
    private final CheckListDAO checkListDAO;
    private final AssetDAO assetDAO;
    private final EstateDAO estateDAO;

    @Override
    public MediaResponseDto getMedia(Long id) {
        return new MediaResponseDto(mediaDAO.findMedia(id));
    }
    @Override
    public MediaResponseDto saveMedia(MediaSaveRequestDto mediaSaveRequestDto) {
        return new MediaResponseDto(mediaDAO.saveMedia(mediaSaveRequestDto.toEntity()));
    }
    @Override
    public MediaResponseDto saveEstateMedia(Long estateId, MediaSaveRequestDto mediaSaveRequestDto) {
        return new MediaResponseDto(mediaDAO.saveEstateMedia(estateDAO.findEstate(estateId),mediaSaveRequestDto.toEntity()));
    }

    @Override
    public MediaResponseDto saveAssetMedia(Long assetId, MediaSaveRequestDto mediaSaveRequestDto) {
        return new MediaResponseDto(mediaDAO.saveAssetMedia(assetDAO.findAsset(assetId),mediaSaveRequestDto.toEntity()));
    }

    @Override
    public MediaResponseDto saveCheckListMedia(Long checklistId, MediaSaveRequestDto mediaSaveRequestDto) {
        return new MediaResponseDto(mediaDAO.saveCheckListMedia(checkListDAO.findCheckList(checklistId),mediaSaveRequestDto.toEntity()));
    }


    @Override
    public MediaResponseDto updateMedia(Long id, MediaUpdateRequestDto mediaUpdateRequestDto) {
        return new MediaResponseDto(mediaDAO.updateMedia(id, mediaUpdateRequestDto.getOrigFileName(), mediaUpdateRequestDto.getFilePath()));
    }
}
*/

