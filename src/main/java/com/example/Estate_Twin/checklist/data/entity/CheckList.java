package com.example.Estate_Twin.checklist.data.entity;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
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
    private Boolean tenentConfirmYN;
    private Boolean ownerConfirmYN;
    @ManyToOne
    @JoinColumn(name = "ASSET_ID")
    private Asset asset;

    @Builder
    public CheckList(String flawPart, Boolean brokerConfirmYN, Boolean ownerConfirmYN,
                     String checkListContent, LocalDateTime repairDate, String checkListPhoto,
                     RepairType repairType, Boolean tenentConfirmYN) {
        this.flawPart = flawPart;
        this.brokerConfirmYN = brokerConfirmYN;
        this.repairDate = repairDate;
        this.ownerConfirmYN = ownerConfirmYN;
        this.checkListContent = checkListContent;
        this.repairType = repairType;
        this.tenentConfirmYN = tenentConfirmYN;
        this.checkListPhoto = checkListPhoto;
    }

    public CheckList update(CheckListUpdateRequestDto dto) {
        this.flawPart = dto.getFlawPart();
        this.brokerConfirmYN = dto.getBrokerConfirmYN();
        this.repairDate = dto.getRepairDate();
        this.ownerConfirmYN = dto.getOwnerConfirmYN();
        this.checkListContent = dto.getCheckListContent();
        this.repairType = RepairType.of(dto.getRepairType());
        this.tenentConfirmYN = dto.getTenentConfirmYN();
        this.checkListPhoto = dto.getCheckListPhoto();
        return this;
    }

    public CheckList setBrokerConfirmY() {
        this.brokerConfirmYN = true;
        return this;
    }

    public CheckList setOwnerConfirmY() {
        this.ownerConfirmYN = true;
        return this;
    }

    public CheckList setTenentConfirmY() {
        this.tenentConfirmYN = true;
        return this;
    }

    public void setAsset(Asset asset) {
        if(this.asset != null) {
            this.asset.getCheckLists().remove(this);
        }
        this.asset = asset;
        asset.getCheckLists().add(this);
    }

}
