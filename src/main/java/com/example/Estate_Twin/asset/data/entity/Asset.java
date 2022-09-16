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
    @Column(nullable = false)
    private String assetName;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String manufacturer;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private Estate estate;
    @OneToMany(mappedBy = "asset", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Media> assetPhoto = new ArrayList<>();
    @OneToMany(mappedBy = "asset", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<CheckList> checkList = new ArrayList<>();

    @Builder // 빌더 형태로 만들어줌
    public Asset(Category category, String assetName, String productName, String manufacturer, Estate estate) {
        this.category = category;
        this.assetName = assetName;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.estate = estate;
    }
    public void setEstate(Estate estate) {
        if(this.estate != null) {
            this.estate.getAssets().remove(this);
        }
        this.estate = estate;
        estate.getAssets().add(this);
    }
    public Asset setId(Long id) {
        this.id = id;
        return this;
    }

}
