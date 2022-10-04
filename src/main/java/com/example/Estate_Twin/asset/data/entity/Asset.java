package com.example.Estate_Twin.asset.data.entity;

import com.example.Estate_Twin.asset.web.dto.AssetUpdateRequestDto;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "asset")
public class Asset extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_id")
    private Long id;
    private String productName;
    private String manufacturer;
    //AR Camera에서 각 앵커마다 에셋이 존재하기 때문
    private String anchorId;
    private String assetPhoto;
    private LocalDateTime repairDate;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Option option;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private Estate estate;
    @OneToMany(mappedBy = "asset", orphanRemoval = true)
    private List<CheckList> checkLists;

    @Builder // 빌더 형태로 만들어줌
    public Asset(Category category, String assetPhoto, Option option, String productName, String manufacturer, String anchorId, LocalDateTime repairDate) {
        this.category = category;
        this.option = option;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.anchorId = anchorId;
        this.repairDate = repairDate;
        this.assetPhoto = assetPhoto;
    }

    public Asset update(AssetUpdateRequestDto dto) {
        this.category = Category.of(dto.getCategory());
        this.option = Option.of(dto.getOption());
        this.productName = dto.getProductName();
        this.manufacturer = dto.getManufacturer();
        this.anchorId = dto.getAnchorId();
        this.repairDate = dto.getRepairDate();
        this.assetPhoto = dto.getAssetPhoto();
        return this;
    }

    public void setEstate(Estate estate) {
        if(this.estate != null) {
            this.estate.getAssets().remove(this);
        }
        this.estate = estate;
        this.estate.getAssets().add(this);
    }

    @PrePersist
    public void prePersist() {
        this.checkLists = new ArrayList<>();
    }

}
