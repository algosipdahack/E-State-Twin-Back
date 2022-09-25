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

    @Column(columnDefinition = "TEXT")
    private String checkListContent;

    @Column
    private LocalDateTime repairDate;

    @Enumerated(EnumType.STRING)
    private RepairType repairType;

    @Column
    private Boolean brokerConfirmYN;

    @Column
    private Boolean tanentConfirmYN;

    @Column
    private Boolean ownerConfirmYN;

    @ManyToOne
    @JoinColumn(name = "ASSET_ID")
    private Asset asset;

    @OneToMany(mappedBy = "checkList", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Media> checkListPhoto = new HashSet<>();

    @Builder
    public CheckList(String flawPart, Boolean brokerConfirmYN, Boolean ownerConfirmYN,
                     String checkListContent, LocalDateTime repairDate,
                     RepairType repairType, Boolean tanentConfirmYN) {
        this.flawPart = flawPart;
        this.brokerConfirmYN = brokerConfirmYN;
        this.repairDate = repairDate;
        this.ownerConfirmYN = ownerConfirmYN;
        this.checkListContent = checkListContent;
        this.repairType = repairType;
        this.tanentConfirmYN = tanentConfirmYN;
    }


    public void setAsset(Asset asset) {
        if(this.asset != null) {
            this.asset.getCheckLists().remove(this);
        }
        this.asset = asset;
        asset.getCheckLists().add(this);
    }
}
