package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.contractstate.domain.entity.ContractState;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.user.domain.entity.*;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

import static com.example.Estate_Twin.contractstate.domain.entity.State.BEFORE;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "estate")
public class Estate extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estate_id")
    private Long id;

    //리스트에서 보여줄 썸네일
    @Column
    private String estateThumbNail;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private String model;

    @Column
    private String city;

    @Column
    private String borough;

    @Column
    private String town;

    @Column
    private State state;

    @Column
    private String thumbnail3D;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @OneToOne(mappedBy = "estate")
    private EstateHit estateHit;

    @OneToOne(mappedBy = "estate")
    private Address address;


    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(name = "broker_id")
    private Broker broker;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(
            mappedBy = "estate",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Media> estateMedia;

    @Builder // 빌더 형태로 만들어줌
    public Estate(String content, Rank rank, String model, String town,
                  TransactionType transactionType, String estateThumbNail,
                  String city, String borough,String thumbnail3D, Address address
    ) {
        this.borough = borough;
        this.content = content;
        this.rank = rank;
        this.model = model;
        this.thumbnail3D = thumbnail3D;
        this.city = city;
        this.address = address;
        this.transactionType = transactionType;
        this.estateThumbNail = estateThumbNail;
        this.town = town;
    }

    //아예 초기화한 후 대입
    public void addMedia(List<Media> mediaList) {
        this.estateMedia.clear();
        this.estateMedia.addAll(mediaList);
    }

    public void setHouse(House house) {
        this.house = house;
        house.setEstate(this);
    }

    public void setAddress(Address address) {
        this.address = address;
        address.setEstate(this);
    }

    public void setEstateHit(EstateHit estateHit) {
        this.estateHit = estateHit;
        estateHit.setEstate(this);
    }

    public void setState(State state) {
        this.state = state;
    }
    //insert 되기 전 실행된다
    @PrePersist
    public void prePersist() {
        this.estateMedia = new ArrayList<>();
        this.state = this.state == null ? BEFORE : this.state;
    }

}
