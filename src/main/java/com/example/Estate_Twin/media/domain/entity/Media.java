package com.example.Estate_Twin.media.domain.entity;

import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.checklist.data.entity.CheckList;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.user.domain.entity.Broker;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Long id;
    private String origFileName;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id", referencedColumnName = "BROKER_ID")
    private Broker broker;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checklist_id", referencedColumnName = "CHECKLIST_ID")
    private CheckList checkList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id", referencedColumnName = "ESTATE_ID")
    private Estate estate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", referencedColumnName = "ASSET_ID")
    private Asset asset;

    @Builder
    public Media(String origFileName, String filePath) {
        this.origFileName = origFileName;
        this.filePath = filePath;
    }
    public void setCheckList(CheckList checkList) {
        if(this.checkList != null) {
            this.checkList.getCheckListPhoto().remove(this);
        }
        this.checkList = checkList;
        checkList.getCheckListPhoto().add(this);
    }
    public void setEstate(Estate estate) {
        if(this.estate != null) {
            this.estate.getEstateMedia().remove(this);
        }
        this.estate = estate;
        estate.getEstateMedia().add(this);
    }
    public void setAsset(Asset asset) {
        if(this.asset != null) {
            this.asset.getAssetPhoto().remove(this);
        }
        this.asset = asset;
        asset.getAssetPhoto().add(this);
    }
    public void setBroker(Broker broker) {
        if(this.broker != null) {
            this.broker.getBrokerPhoto().remove(this);
        }
        this.broker = broker;
        broker.getBrokerPhoto().add(this);
    }

}
