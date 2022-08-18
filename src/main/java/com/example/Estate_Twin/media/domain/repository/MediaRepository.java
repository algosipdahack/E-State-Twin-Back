package com.example.Estate_Twin.media.domain.repository;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.media.domain.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media,Long> {
    public List<Media> findAllByEstateId(Long estateId);
    public List<Media> findAllByAssetId(Long assetId);
    public List<Media> findAllByCheckListId(Long checklistId);

}
