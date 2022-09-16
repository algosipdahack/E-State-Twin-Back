package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.exception.BadRequestException;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.user.domain.entity.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;


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
    private String estateThumbNail;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //s3에 올려진 model src
    private String model;
    private String city;
    private String borough;
    private String town;

    //s3에 올려진 3D 모델의 썸네일
    private String thumbnail3D;
    //TODO 밑의 두개가 confirm되는 순간 true로 바뀜
    private boolean isPosted;
    private boolean ownerConfirmYN;
    private boolean brokerConfirmYN;
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private State state;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estatehit_id")
    private EstateHit estateHit;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id")
    private Broker broker;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tanent_id")
    private User tanent;
    @OneToMany(mappedBy = "estate",cascade = {CascadeType.ALL},fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Media> estateMedia;
    @OneToMany(mappedBy = "estate",cascade = {CascadeType.ALL},fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Asset> assets = new ArrayList<>();
    //찜한 매물
    @OneToMany(mappedBy = "estate",cascade = {CascadeType.ALL},fetch = FetchType.LAZY,orphanRemoval = true)
    private Set<DipEstate> dipEstates = new HashSet<>();

    @Builder // 빌더 형태로 만들어줌
    public Estate(String content, String model, String town,
                  TransactionType transactionType, String estateThumbNail,
                  String city, String borough, String thumbnail3D, Address address) {
        this.borough = borough;
        this.content = content;
        this.model = model;
        this.thumbnail3D = thumbnail3D;
        this.city = city;
        this.address = address;
        this.transactionType = transactionType;
        this.estateThumbNail = estateThumbNail;
        this.town = town;
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
    public void setOwner(User owner) {
        if(this.owner != null ){
            this.owner.getOwnEstates().remove(this);
        }
        this.owner = owner;
        owner.getOwnEstates().add(this);
    }
    public void setBroker(Broker broker) {
        if(this.broker != null ){
            this.broker.getEstates().remove(this);
        }
        this.broker = broker;
        broker.getEstates().add(this);
    }
    public void setTanent(User tanent) {
        this.tanent = tanent;
        tanent.setTanentEstate(this);
    }
    public void setGrade(Grade grade) {
        if(this.isPosted == false) {
            throw new BadRequestException("게시되지 않은 매물은 뱃지를 가질 수 없습니다!");
        }
        this.grade = grade;
    }
    public void setBrokerConfirmY() {
        this.brokerConfirmYN = true;
    }
    public void setOwnerConfirmY() {
        this.ownerConfirmYN = true;
    }
    public void setState(State state) {
        this.state = state;
    }
    public void setId(Long id) { this.id = id; }
    //insert 되기 전 실행된다
    @PrePersist
    public void prePersist() {
        this.estateMedia = new ArrayList<>();
        this.state = this.state == null ? State.CONTRACT_BEFORE : this.state;
        this.isPosted = false;
        this.ownerConfirmYN = false;
        this.brokerConfirmYN = false;
    }

}
