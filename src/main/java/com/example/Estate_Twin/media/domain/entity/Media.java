package com.example.Estate_Twin.media.domain.entity;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;

import javax.persistence.*;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHECKLIST_ID", referencedColumnName = "CHECKLIST_ID")
    private CheckList checkList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ESTATE_ID", referencedColumnName = "ESTATE_ID")
    private Estate estate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID", referencedColumnName = "ASSET_ID")
    private Asset asset;


    @Builder
    public Media(String origFileName, String filePath) {
        this.origFileName = origFileName;
        this.filePath = filePath;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

}
