package com.example.Estate_Twin.domain.estate;

import com.example.Estate_Twin.domain.photo.Photo;
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
    private List<Photo> EstatePhoto = new ArrayList<>();

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @OneToMany(
            mappedBy = "Estate",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Photo> EstateVideo = new ArrayList<>();

    private Rank rank;

    private String Model;

    private String ARCam;


}
