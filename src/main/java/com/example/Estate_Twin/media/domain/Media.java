package com.example.Estate_Twin.media.domain;

import com.example.Estate_Twin.asset.domain.Asset;
import com.example.Estate_Twin.checklist.domain.CheckList;
import com.example.Estate_Twin.estate.domain.Estate;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private int id;

    @Column
    private String origFileName;

    @Column
    private String filePath;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "upload_time")
    private Date uploadTime;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ESTATE_ID", referencedColumnName = "ESTATE_ID")
    private Estate estate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID", referencedColumnName = "ASSET_ID")
    private Asset asset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHECKLIST_ID", referencedColumnName = "CHECKLIST_ID")
    private CheckList checkList;

    @Builder
    public Media(String origFileName, String filePath, Type type,
                 Estate estate, Asset asset, CheckList checkList
    ) {
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.uploadTime = new Date();
        this.type = type;
        this.estate = estate;
        this.asset = asset;
        this.checkList = checkList;
    }


}
