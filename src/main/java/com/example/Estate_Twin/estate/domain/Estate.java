package com.example.Estate_Twin.estate.domain;

import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.constractstate.domain.ConstractState;
import com.example.Estate_Twin.house.domain.House;
import com.example.Estate_Twin.media.domain.Media;
import com.example.Estate_Twin.user.domain.Broker;
import com.example.Estate_Twin.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "estate")
public class Estate extends BaseTimeEntity {
    @Id
    @Column(name = "estate_id")
    private Long estateId;

    @OneToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id")
    private Broker broker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(
            mappedBy = "estate",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Media> estateMedia = new ArrayList<>();


    @OneToOne(mappedBy = "estate")
    private ConstractState constractState;

    @OneToOne(mappedBy = "estate")
    private EstateHit estateHit;

    @Column
    private TransactionType transactionType;

    //리스트에서 보여줄 썸네일
    @Column
    private String estateThumbNail;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column
    private String model;

    @Column
    private String arCam;

    @Column
    private String city;

    @Column
    private String ad_distinct;

    @Column
    private String address;


    @Builder // 빌더 형태로 만들어줌
    public Estate(List<Media> estateMedia, String content, Rank rank, String model, String arCam,
                  House house, Broker broker, User owner, ConstractState constractState,
                  EstateHit estateHit, TransactionType transactionType, String estateThumbNail,
                  String city, String ad_distinct, String address
    ) {
        this.estateMedia = estateMedia;
        this.broker = broker;
        this.estateHit = estateHit;
        this.ad_distinct = ad_distinct;
        this.content = content;
        this.rank = rank;
        this.model = model;
        this.arCam = arCam;
        this.house = house;
        this.owner = owner;
        this.city = city;
        this.address = address;
        this.transactionType = transactionType;
        this.estateThumbNail = estateThumbNail;
        this.constractState = constractState;
    }

    public void update(Broker broker, List<Media> estateMedia, ConstractState constractState, EstateHit estateHit,
                       TransactionType transactionType, String estateThumbNail, String content, Rank rank,
                       String model, String arCam, String city, String ad_distinct, String address) {
        this.broker = broker;
        this.estateMedia = estateMedia;
        this.constractState = constractState;
        this.estateHit = estateHit;
        this.transactionType = transactionType;
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.rank = rank;
        this.model = model;
        this.arCam = arCam;
        this.city = city;
        this.ad_distinct = ad_distinct;
        this.address = address;
    }
}
