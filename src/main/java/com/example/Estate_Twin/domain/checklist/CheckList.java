package com.example.Estate_Twin.domain.checklist;

import com.example.Estate_Twin.domain.asset.Asset;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.domain.media.Media;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "checklist")
public class CheckList extends BaseTimeEntity {

    @EmbeddedId
    @Column(name = "checklist_id")
    private CheckListId id;

    @MapsId("assetId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ASSET_ID"),
            @JoinColumn(name = "HOUSE_ID")
    })
    private Asset asset;


    /*@OneToMany(
            mappedBy = "checkList",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )

    private List<Media> checkListPhoto = new ArrayList<>();*/

    @Column
    private String flawPart;

    @Column
    private String category;

    @Column(columnDefinition = "TEXT")
    private String checkListContent;

    @Column
    private Date repairDate;

    @Enumerated(EnumType.STRING)
    private RepairType repairType;

    @Column
    private String manufacturer;

    @Column
    private Boolean brokerConfirmYN;

    @Column
    private Boolean ownerConfirmYN;

    @Builder // 빌더 형태로 만들어줌
    public CheckList(List<Media> checkListPhoto, String category, String checkListContent,
                     RepairType repairType, String manufacturer, Asset asset
    ) {//생성자
        //this.checkListPhoto = checkListPhoto;
        this.category = category;
        this.checkListContent = checkListContent;
        this.repairType = repairType;
        this.manufacturer = manufacturer;
        this.asset = asset;
    }
}
