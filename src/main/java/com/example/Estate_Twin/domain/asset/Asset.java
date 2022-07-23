package com.example.Estate_Twin.domain.asset;

import com.example.Estate_Twin.domain.BaseTimeEntity;
import com.example.Estate_Twin.domain.media.Media;
import com.example.Estate_Twin.domain.media.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Asset extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_id")
    private long id;

    private Category category;

    @OneToMany(
            mappedBy = "Asset",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )

    private List<Media> assetPhoto = new ArrayList<>();


    @Column(nullable = false)
    private String assetName;

    @Column(nullable = false)
    private String productName;

}
