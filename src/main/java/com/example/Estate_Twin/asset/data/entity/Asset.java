package com.example.Estate_Twin.asset.data.entity;

import com.example.Estate_Twin.checklist.data.entity.*;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.util.BaseTimeEntity;
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
    private Option option;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String manufacturer;
    //AR Camera에서 각 앵커마다 에셋이 존재하기 때문
    private String anchorId;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private Estate estate;
    @OneToMany(mappedBy = "asset", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Media> assetPhoto = new HashSet<>();
    @OneToMany(mappedBy = "asset", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<CheckList> checkList = new HashSet<>();

    @Builder // 빌더 형태로 만들어줌
    public Asset(Category category, Option option, String productName, String manufacturer, String anchorId, Estate estate) {
        this.category = category;
        this.option = option;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.anchorId = anchorId;
        this.estate = estate;
    }
    public void setEstate(Estate estate) {
        if(this.estate != null) {
            this.estate.getAssets().remove(this);
        }
        this.estate = estate;
        this.estate.getAssets().add(this);
    }
    public Asset setId(Long id) {
        this.id = id;
        return this;
    }

}
