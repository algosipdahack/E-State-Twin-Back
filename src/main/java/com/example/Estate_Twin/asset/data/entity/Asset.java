package com.example.Estate_Twin.asset.data.entity;

import com.example.Estate_Twin.asset.web.dto.AssetUpdateRequestDto;
import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @OneToMany(mappedBy = "asset", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CheckList> checkLists = new ArrayList<>();

    @Builder // 빌더 형태로 만들어줌
    public Asset(Category category, String assetPhoto, String productName, String manufacturer, String anchorId) {
        this.category = category;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.anchorId = anchorId;
        this.assetPhoto = assetPhoto;
    }

    public Asset update(AssetUpdateRequestDto dto) {
        this.category = Category.of(dto.getCategory());
        this.productName = dto.getProductName();
        this.manufacturer = dto.getManufacturer();
        this.anchorId = dto.getAnchorId();
        this.assetPhoto = dto.getAssetPhoto();
        return this;
    }

    public void setEstate(Estate estate) {
        if(this.estate != null) {
            this.estate.getAssets().remove(this);
        }
        this.estate = estate;
        estate.getAssets().add(this);
    }

}
