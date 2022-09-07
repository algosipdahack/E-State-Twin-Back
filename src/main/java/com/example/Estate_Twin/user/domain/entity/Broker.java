package com.example.Estate_Twin.user.domain.entity;

import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.media.domain.entity.Media;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "broker")
public class Broker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broker_id")
    private Long id;

    //단방향
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // broker column
    @OneToMany(
            mappedBy = "broker",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Media> brokerPhoto;

    @Column
    private String businessName;

    @Column
    private String agentName;

    @Column
    private String brokerageRegistrationNumber;

    @Column
    private String businessRegistrationNumber;

    @Column
    private String businessLicense;

    @Column
    private String brokerageRegistrationLicense;

    @OneToMany(
            mappedBy = "broker",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Estate> estates = new ArrayList<>();


    public void addMedia(List<Media> mediaList) {
        this.brokerPhoto.clear();
        this.brokerPhoto.addAll(mediaList);
    }
}
