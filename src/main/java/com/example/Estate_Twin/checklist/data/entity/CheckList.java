package com.example.Estate_Twin.checklist.data.entity;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "checklist")
public class CheckList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checklist_id")
    private Long id;

    @Column
    private String flawPart;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String checkListContent;

    @Column
    private LocalDateTime repairDate;

    @Enumerated(EnumType.STRING)
    private RepairType repairType;

    @Column
    private String manufacturer;

    @Column
    private Boolean brokerConfirmYN;

    @Column
    private Boolean tanentConfirmYN;

    @Column
    private Boolean ownerConfirmYN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID")
    private Asset asset;

    @OneToMany(
            mappedBy = "checkList",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Media> checkListPhoto = new ArrayList<>();

    @Builder
    public CheckList(String flawPart, Boolean brokerConfirmYN, Boolean ownerConfirmYN,
                     Category category, String checkListContent, LocalDateTime repairDate,
                     RepairType repairType, String manufacturer, Boolean tanentConfirmYN) {
        this.flawPart = flawPart;
        this.brokerConfirmYN = brokerConfirmYN;
        this.repairDate = repairDate;
        this.ownerConfirmYN = ownerConfirmYN;
        this.category = category;
        this.checkListContent = checkListContent;
        this.repairType = repairType;
        this.manufacturer = manufacturer;
        this.tanentConfirmYN = tanentConfirmYN;
    }

    public void addMedia(List<Media> mediaList) {
        this.checkListPhoto.clear();
        this.checkListPhoto.addAll(mediaList);
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
        this.asset.getCheckList().add(this);
    }
}
