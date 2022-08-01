package com.example.Estate_Twin.domain.asset;

import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.domain.house.House;
import com.example.Estate_Twin.domain.media.Media;
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
    @EmbeddedId
    @Column(name = "asset_id")
    private AssetId id;

    @MapsId("houseId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House houseId;

    @Column
    private String category;

    /*@OneToMany(
            mappedBy = "asset",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Media> assetPhoto = new ArrayList<>();
*/
    @Column
    private String assetName;

    @Column
    private String productName;

    @Builder // 빌더 형태로 만들어줌
    public Asset(String category, List<Media> assetPhoto, String assetName, String productName) {//생성자
        this.category = category;
        //this.assetPhoto = assetPhoto;
        this.assetName = assetName;
        this.productName = productName;
    }
}
