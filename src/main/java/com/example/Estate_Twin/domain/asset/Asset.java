package com.example.Estate_Twin.domain.asset;

import com.example.Estate_Twin.domain.BaseTimeEntity;
import com.example.Estate_Twin.domain.media.Media;
import com.example.Estate_Twin.domain.media.Type;
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
    private long id;

    @Enumerated(EnumType.STRING)
    private Category category;

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
    public Asset(Category category, List<Media> assetPhoto, String assetName, String productName) {//생성자
        this.category = category;
        this.assetPhoto = assetPhoto;
        this.assetName = assetName;
        this.productName = productName;
    }
}
