package com.example.Estate_Twin.domain.estate;

import com.example.Estate_Twin.domain.checklist.CheckList;
import com.example.Estate_Twin.domain.media.Media;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estate_id")
    private long id;

    @OneToMany(
            mappedBy = "Estate",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Media> EstateMedia = new ArrayList<>();

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @OneToMany(
            mappedBy = "Estate",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<CheckList> checkList = new ArrayList<>();


    private Rank rank;

    private String Model;

    private String ARCam;


}
