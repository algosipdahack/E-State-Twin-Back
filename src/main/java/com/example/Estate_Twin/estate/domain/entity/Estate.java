package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.asset.data.entity.Asset;
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

    //s3에 올려진 model src
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

    //s3에 올려진 3D 모델의 썸네일
    @Column
    private String thumbnail3D;

    @Column
    private boolean isPosted;

    @Column
    private boolean ownerConfirmYN;

    @Column
    private boolean brokerConfirmYN;

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

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(name = "tanent_id")
    private User tanent;

    @OneToMany(
            mappedBy = "estate",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Media> estateMedia;


    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL)
    private Set<DipEstate> dipEstates = new HashSet<>();

    @Builder // 빌더 형태로 만들어줌
    public Estate(String content, Rank rank, String model, String town,
                  TransactionType transactionType, String estateThumbNail,
                  String city, String borough, String thumbnail3D, Address address) {
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
    public void setOwner(User owner) {
        this.owner = owner;
        this.owner.getOwnEstate().add(this);
    }
    public void setBroker(Broker broker) {
        this.broker = broker;
        this.broker.getEstates().add(this);
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

    public void setTanent(User tanent) {
        this.tanent = tanent;
        tanent.setEstate(this);
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
        this.isPosted = false;
        this.ownerConfirmYN = false;
        this.brokerConfirmYN = false;
    }

}
