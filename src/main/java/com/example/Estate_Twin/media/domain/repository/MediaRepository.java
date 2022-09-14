package com.example.Estate_Twin.media.domain.repository;

import com.example.Estate_Twin.media.domain.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media,Long> {
    List<Media> findAllByEstateId(Long estateId);
    List<Media> findAllByAssetId(Long assetId);
    List<Media> findAllByCheckListId(Long checklistId);

}
