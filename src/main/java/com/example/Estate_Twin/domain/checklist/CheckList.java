package com.example.Estate_Twin.domain.checklist;

import com.example.Estate_Twin.domain.BaseTimeEntity;
import com.example.Estate_Twin.domain.asset.Asset;
import com.example.Estate_Twin.domain.asset.Category;
import com.example.Estate_Twin.domain.estate.Estate;
import com.example.Estate_Twin.domain.estate.Rank;
import com.example.Estate_Twin.domain.media.Media;
import com.example.Estate_Twin.domain.media.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CheckList extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checklist_id")
    private long id;

    @OneToMany(
            mappedBy = "CheckList",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )

    private List<Media> checklistphoto = new ArrayList<>();

    //하자부위
    private Category category;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String checkListContent;

    private String modelName;

    private RepairType repairType;

    private String manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    private Asset asset;

}
