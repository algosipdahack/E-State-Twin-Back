package com.example.Estate_Twin.domain.checklist;

import com.example.Estate_Twin.domain.asset.AssetId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class CheckListId implements Serializable {
    @Embedded
    private AssetId assetId;
    private String checklistId;

    public AssetId getAssetId() {
        return assetId;
    }

    public void setAssetId(AssetId assetId) {
        this.assetId = assetId;
    }
}
