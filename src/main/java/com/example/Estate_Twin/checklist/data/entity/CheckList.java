package com.example.Estate_Twin.checklist.data.entity;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "checklist")
public class CheckList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checklist_id")
    private Long id;
    private String flawPart;
    @Column(columnDefinition = "TEXT")
    private String checkListContent;
    private String checkListPhoto;
    private LocalDateTime repairDate;
    @Enumerated(EnumType.STRING)
    private RepairType repairType;
    private Boolean brokerConfirmYN;
    private Boolean tanentConfirmYN;
    private Boolean ownerConfirmYN;
    @ManyToOne
    @JoinColumn(name = "ASSET_ID")
    private Asset asset;

    @Builder
    public CheckList(String flawPart, Boolean brokerConfirmYN, Boolean ownerConfirmYN,
                     String checkListContent, LocalDateTime repairDate, String checkListPhoto,
                     RepairType repairType, Boolean tanentConfirmYN) {
        this.flawPart = flawPart;
        this.brokerConfirmYN = brokerConfirmYN;
        this.repairDate = repairDate;
        this.ownerConfirmYN = ownerConfirmYN;
        this.checkListContent = checkListContent;
        this.repairType = repairType;
        this.tanentConfirmYN = tanentConfirmYN;
        this.checkListPhoto = checkListPhoto;
    }

    public void setAsset(Asset asset) {
        if(this.asset != null) {
            this.asset.getCheckLists().remove(this);
        }
        this.asset = asset;
        asset.getCheckLists().add(this);
    }
}
