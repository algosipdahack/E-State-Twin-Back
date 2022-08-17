package com.example.Estate_Twin.asset.domain;

import com.example.Estate_Twin.checklist.domain.CheckList;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.house.domain.House;
import com.example.Estate_Twin.media.domain.Media;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor
@Entity
@Table(name = "asset")
public class Asset extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    @JsonIgnore
    private House house;

    @Column
    private String category;

    @OneToMany(
            mappedBy = "asset",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Media> assetPhoto = new ArrayList<>();

    @OneToMany(
            mappedBy = "asset",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JsonIgnore
    private List<CheckList> checkList = new ArrayList<>();

    @Column
    private String assetName;

    @Column
    private String productName;

    @Builder // 빌더 형태로 만들어줌
    public Asset(String category, House house, String assetName, String productName) {
        this.category = category;
        this.house = house;
        this.assetName = assetName;
        this.productName = productName;
    }

    public void addAssetPhoto(List<Media> assetPhoto) {
        for(Media media : assetPhoto) {
            media.setAsset(this);
        }
    }

    public void addCheckList(List<CheckList> checkLists) {
        for(CheckList checkList: checkLists) {
            checkList.setAsset(this);
        }
    }

    public void setHouse(House house) {
        if(this.house != null) {
            this.house.getAssets().remove(this);
        }
        this.house = house;
        this.house.getAssets().add(this);
    }

    public void update(String category, House house, String assetName, String productName) {
        this.category = category;
        this.house = house;
        this.assetName = assetName;
        this.productName = productName;
    }
}
