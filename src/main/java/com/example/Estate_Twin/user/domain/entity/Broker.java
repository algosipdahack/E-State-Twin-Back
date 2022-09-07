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

    // broker column

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

    //단방향
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "broker",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Estate> estates = new ArrayList<>();

    @OneToMany(
            mappedBy = "broker",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Media> brokerPhoto;


    public void addMedia(Media media) {
        this.brokerPhoto.add(media);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addEstate(Estate estate) {
        this.estates.add(estate);
    }
}
