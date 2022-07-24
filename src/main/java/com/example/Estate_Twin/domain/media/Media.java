package com.example.Estate_Twin.domain.media;

import com.example.Estate_Twin.domain.BaseTimeEntity;
import com.example.Estate_Twin.domain.asset.Asset;
import com.example.Estate_Twin.domain.checklist.CheckList;
import com.example.Estate_Twin.domain.estate.Estate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "media")
public class Media extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Long id;

    @Column
    private String origFileName;

    @Column
    private String filePath;

    @Column
    private Long fileSize;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checklist_id")
    private CheckList checkList;

    @Builder
    public Media(String origFileName, String filePath, Long fileSize, Type type,
                 Estate estate, Asset asset, CheckList checkList
    ) {
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.type = type;
        this.estate = estate;
        this.asset = asset;
        this.checkList = checkList;
    }


}
