package com.example.Estate_Twin.domain.checklist;

import com.example.Estate_Twin.domain.BaseTimeEntity;
import com.example.Estate_Twin.domain.asset.Asset;
import com.example.Estate_Twin.domain.asset.Category;
import com.example.Estate_Twin.domain.estate.Estate;
import com.example.Estate_Twin.domain.estate.Rank;
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
@Table(name = "checklist")
public class CheckList extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checklist_id")
    private long id;

    @OneToMany(
            mappedBy = "checkList",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )

    private List<Media> checkListPhoto = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    //하자부위
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String checkListContent;

    @Column
    private String modelName;

    @Enumerated(EnumType.STRING)
    private RepairType repairType;

    @Column
    private String manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @Builder // 빌더 형태로 만들어줌
    public CheckList(List<Media> checkListPhoto, Category category, String checkListContent, String modelName,
                     RepairType repairType, String manufacturer, Estate estate, Asset asset
    ) {//생성자
        this.checkListPhoto = checkListPhoto;
        this.category = category;
        this.checkListContent = checkListContent;
        this.modelName = modelName;
        this.repairType = repairType;
        this.manufacturer = manufacturer;
        this.estate = estate;
        this.asset = asset;
    }
}
