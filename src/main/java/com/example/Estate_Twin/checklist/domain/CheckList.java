package com.example.Estate_Twin.checklist.domain;

import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.media.domain.Media;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Asset asset;

    @OneToMany(
            mappedBy = "checkList",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Media> checkListPhoto = new ArrayList<>();

    @Column
    private String flawPart;

    @Column
    private String category;

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
                     String category, String checkListContent, LocalDateTime repairDate,
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
                     String category, String checkListContent, LocalDateTime repairDate,
                     RepairType repairType, String manufacturer, Asset asset
    ) {
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
}
