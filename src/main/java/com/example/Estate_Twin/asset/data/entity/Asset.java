package com.example.Estate_Twin.asset.data.entity;

import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.media.domain.entity.Media;
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

    @Column
    private String assetName;

    @Column
    private String productName;

    @Column
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private Estate estate;

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
    private List<CheckList> checkList = new ArrayList<>();

    @Builder // 빌더 형태로 만들어줌
    public Asset(Category category, String assetName, String productName, String manufacturer, Estate estate) {
        this.category = category;
        this.assetName = assetName;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.estate = estate;
    }

    public void addMedia(Media media) {
        this.assetPhoto.add(media);
    }
    public void addCheckList(CheckList checkList) {
        if (this.checkList == null) {
            this.checkList = new ArrayList<>();
        }
        this.checkList.add(checkList);
    }
    public void setEstate(Estate estate) {
        this.estate = estate;
        this.estate.addAsset(this);
    }
    public void setId(Long id) { this.id = id; }

}
