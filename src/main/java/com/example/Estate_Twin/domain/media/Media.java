package com.example.Estate_Twin.domain.media;

import com.example.Estate_Twin.domain.asset.Asset;
import com.example.Estate_Twin.domain.checklist.CheckList;
import com.example.Estate_Twin.domain.estate.Estate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Long id;

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
    @JoinColumns({
            @JoinColumn(name = "ESTATEID", referencedColumnName = "ESTATEID"),
            @JoinColumn(name = "ESTATE_HOUSE_ID", referencedColumnName = "HOUSE_ID"),
            @JoinColumn(name = "BROKER_ID", referencedColumnName = "BROKER_ID"),
            @JoinColumn(name = "OWNER_ID", referencedColumnName = "OWNER_ID")
    })
    private Estate estate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ASSETID", referencedColumnName = "ASSETID"),
            @JoinColumn(name = "ASSET_HOUSE_ID", referencedColumnName = "HOUSE_ID"),
    })
    private Asset asset;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CHECKLISTID", referencedColumnName = "CHECKLISTID"),
            @JoinColumn(name = "ASSET_ID", referencedColumnName = "ASSET_ID")
    })
    private CheckList checkList;*/

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
        //this.checkList = checkList;
    }


}
