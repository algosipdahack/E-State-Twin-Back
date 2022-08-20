package com.example.Estate_Twin.asset.data.entity;

import com.example.Estate_Twin.checklist.data.entity.*;
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

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

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
    public Asset(Category category, String assetName, String productName) {
        this.category = category;
        this.assetName = assetName;
        this.productName = productName;
    }

    public void addMedia(List<Media> mediaList) {
        this.assetPhoto = mediaList;
    }

}
