package com.example.Estate_Twin.checklist.data.entity;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.web.dto.CheckListUpdateRequestDto;
import com.example.Estate_Twin.user.domain.entity.User;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.*;
import java.util.Optional;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate repairDate;
    @Enumerated(EnumType.STRING)
    private RepairType repairType;
    private Boolean brokerConfirmYN = false;
    private Boolean tenantConfirmYN = false;
    private Boolean ownerConfirmYN = false;
    private Boolean totalConfirmYN = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID")
    private Asset asset;

    @Version
    private Long version;

    @Builder
    public CheckList(String flawPart,String checkListContent, LocalDate repairDate, String checkListPhoto,RepairType repairType) {
        this.flawPart = flawPart;
        this.repairDate = repairDate;
        this.checkListContent = checkListContent;
        this.repairType = repairType;
        this.checkListPhoto = checkListPhoto;
    }

    public void update(CheckListUpdateRequestDto dto) {
        this.flawPart = dto.getFlawPart();
        this.repairDate = dto.getRepairDate();
        this.checkListContent = dto.getCheckListContent();
        this.repairType = RepairType.of(dto.getRepairType());
        this.checkListPhoto = dto.getCheckListPhoto();
    }

    public void setConfirmY(User user) {
        if(user.isBroker()) this.brokerConfirmYN = true;
        else this.ownerConfirmYN = true;
        setTotalConfirmY();
    }
    public void setOwnerConfirmY() {
        this.ownerConfirmYN = true;
    }
    public CheckList setContent(String content) {
        this.checkListContent = content;
        return this;
    }
    public void setTenantConfirmY() {
        this.tenantConfirmYN = true;
    }
    public void setTotalConfirmY() {
        if(brokerConfirmYN && ownerConfirmYN) this.totalConfirmYN = true;
    }

    public void setAsset(Asset asset) {
        Optional<Asset> asset1 = Optional.of(this.asset);
        asset1.ifPresent(it -> it.getCheckLists().remove(this));
        this.asset = asset;
        asset.getCheckLists().add(this);
    }

}
