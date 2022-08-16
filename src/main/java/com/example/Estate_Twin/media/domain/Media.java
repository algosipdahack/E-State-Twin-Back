package com.example.Estate_Twin.media.domain;

import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.checklist.domain.CheckList;
import com.example.Estate_Twin.estate.domain.Estate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Long id;

    @Column
    private String origFileName;

    @Column
    private String filePath;

    @Column(name = "upload_time")
    private LocalDateTime uploadTime;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ESTATE_ID", referencedColumnName = "ESTATE_ID")
    @JsonIgnore
    private Estate estate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID", referencedColumnName = "ASSET_ID")
    @JsonIgnore
    private Asset asset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHECKLIST_ID", referencedColumnName = "CHECKLIST_ID")
    @JsonIgnore
    private CheckList checkList;

    @Builder
    public Media(String origFileName, String filePath, Type type,
                 Asset asset, CheckList checkList
    ) {
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.uploadTime = LocalDateTime.now();
        this.type = type;
        this.asset = asset;
        this.checkList = checkList;
    }
    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public void setAsset(Asset asset) {
        if(this.asset != null) {
            this.asset.getAssetPhoto().remove(this);
        }
        this.asset = asset;
        this.asset.getAssetPhoto().add(this);
    }

}
