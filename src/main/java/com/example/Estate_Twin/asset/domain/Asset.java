package com.example.Estate_Twin.asset.domain;

import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.house.domain.House;
import com.example.Estate_Twin.media.domain.Media;
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
    private House house;

    @Column
    private String category;

    @OneToMany(
            mappedBy = "asset",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Media> assetPhoto = new ArrayList<>();

    @Column
    private String assetName;

    @Column
    private String productName;

    @Builder // 빌더 형태로 만들어줌
    public Asset(String category, House house, List<Media> assetPhoto, String assetName, String productName) {
        this.category = category;
        this.assetPhoto = assetPhoto;
        this.house = house;
        this.assetName = assetName;
        this.productName = productName;
    }
}
