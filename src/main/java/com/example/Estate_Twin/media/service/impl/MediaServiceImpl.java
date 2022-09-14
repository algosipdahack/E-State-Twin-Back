package com.example.Estate_Twin.media.service.impl;

import com.example.Estate_Twin.asset.data.dao.AssetDAO;
import com.example.Estate_Twin.checklist.data.dao.CheckListDAO;
import com.example.Estate_Twin.estate.domain.dao.EstateDAO;
import com.example.Estate_Twin.media.domain.dao.MediaDAO;
import com.example.Estate_Twin.media.service.MediaService;
import com.example.Estate_Twin.media.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public MediaResponseDto updateMedia(Long id, MediaUpdateRequestDto mediaUpdateRequestDto) {
        return new MediaResponseDto(mediaDAO.updateMedia(id, mediaUpdateRequestDto.getOrigFileName(), mediaUpdateRequestDto.getFilePath()));
    }

    @Override
    public MediaResponseDto updateCheckList(Long id, Long checkListId) {
        return new MediaResponseDto(mediaDAO.updateCheckList(id, checkListDAO.findCheckList(checkListId)));
    }

    @Override
    public MediaResponseDto updateAsset(Long id, Long assetId) {
        return new MediaResponseDto(mediaDAO.updateAsset(id, assetDAO.findAsset(assetId)));
    }

    @Override
    public MediaResponseDto updateEstate(Long id, Long estateId) {
        return new MediaResponseDto(mediaDAO.updateEstate(id, estateDAO.findEstate(estateId)));
    }
}
