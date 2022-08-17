package com.example.Estate_Twin.checklist.domain;

import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.media.domain.Media;
import com.fasterxml.jackson.annotation.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID")
    @JsonIgnore
    private Asset asset;

    @OneToMany(
            mappedBy = "checkList",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Media> checkListPhoto = new ArrayList<>();

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
    private Boolean ownerConfirmYN;

    @Builder // 빌더 형태로 만들어줌
    public CheckList(List<Media> checkListPhoto, String flawPart, Boolean brokerConfirmYN, Boolean ownerConfirmYN,
                     Category category, String checkListContent, LocalDateTime repairDate,
                     RepairType repairType, String manufacturer, Asset asset
    ) {
        this.checkListPhoto = checkListPhoto;
        this.flawPart = flawPart;
        this.brokerConfirmYN = brokerConfirmYN;
        this.repairDate = repairDate;
        this.ownerConfirmYN = ownerConfirmYN;
        this.category = category;
        this.checkListContent = checkListContent;
        this.repairType = repairType;
        this.manufacturer = manufacturer;
        this.asset = asset;
    }

    public void update(String flawPart, Boolean brokerConfirmYN, Boolean ownerConfirmYN,
                     Category category, String checkListContent, LocalDateTime repairDate,
                     RepairType repairType, String manufacturer
    ) {
        this.flawPart = flawPart;
        this.brokerConfirmYN = brokerConfirmYN;
        this.repairDate = repairDate;
        this.ownerConfirmYN = ownerConfirmYN;
        this.category = category;
        this.checkListContent = checkListContent;
        this.repairType = repairType;
        this.manufacturer = manufacturer;
    }

    public void setAsset(Asset asset) {
        if (this.asset != null) {
            this.asset.getCheckList().remove(this);
        }
        this.asset = asset;
        this.asset.getCheckList().add(this);
    }
}
