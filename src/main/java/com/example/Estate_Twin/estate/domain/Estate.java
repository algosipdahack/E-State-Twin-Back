package com.example.Estate_Twin.estate.domain;

import com.example.Estate_Twin.contractstate.domain.State;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.contractstate.domain.ContractState;
import com.example.Estate_Twin.house.domain.House;
import com.example.Estate_Twin.media.domain.Media;
import com.example.Estate_Twin.user.domain.Broker;
import com.example.Estate_Twin.user.domain.User;
import com.example.Estate_Twin.util.converter.TransactionTypeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.*;

import static com.example.Estate_Twin.contractstate.domain.State.BEFORE;

@Getter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "estate")
public class Estate extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estate_id")
    private Long id;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(name = "house_id")
    @JsonIgnore
    private House house;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(name = "broker_id")
    @JsonIgnore
    private Broker broker;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;

    @OneToMany(
            mappedBy = "estate",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Media> estateMedia = new ArrayList<>();


    @OneToOne(mappedBy = "estate")
    private ContractState contractState;

    @OneToOne(mappedBy = "estate")
    private EstateHit estateHit;

    @Convert(converter = TransactionTypeConverter.class)
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
    private String borough;

    @Column
    private String address;

    @Column
    private State state = BEFORE;


    @Builder // 빌더 형태로 만들어줌
    public Estate(String content, Rank rank, String model, String arCam,
                  House house, Broker broker, User owner, ContractState contractState,
                  EstateHit estateHit, TransactionType transactionType, String estateThumbNail,
                  String city, String borough, String address
    ) {
        this.broker = broker;
        this.estateHit = estateHit;
        this.borough = borough;
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
        this.contractState = contractState;
    }

    public void update(TransactionType transactionType, String estateThumbNail, String content, Rank rank,
                       String model, String arCam, String city, String borough, String address) {
        this.transactionType = transactionType;
        this.estateThumbNail = estateThumbNail;
        this.content = content;
        this.rank = rank;
        this.model = model;
        this.arCam = arCam;
        this.city = city;
        this.borough = borough;
        this.address = address;
    }

    public void addEstateMedia(List<Media> estateMedia) {
        for(Media media : estateMedia) {
            media.setEstate(this);
        }
    }
}
