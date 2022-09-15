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


    //s3에 올려진 3D 모델의 썸네일
    @Column
    private String thumbnail3D;

    //TODO 밑의 두개가 confirm되는 순간 true로 바뀜
    @Column
    private boolean isPosted;

    @Column
    private boolean ownerConfirmYN;

    @Column
    private boolean brokerConfirmYN;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private Grade grade;

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
    @OneToMany(
            mappedBy = "estate",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true // DB에서 함께 삭제됨
    )
    private List<Asset> assets = new ArrayList<>();

    //찜한 매물
    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL)
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
    public void setGrade(Grade grade) {
        if(this.isPosted == false) {
            throw new BadRequestException("게시되지 않은 매물은 뱃지를 가질 수 없습니다!");
        }
        this.grade = grade;
    }
    public void setOwner(User owner) {
        this.owner = owner;
        this.owner.addOwnEstate(this);
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
        this.broker.addEstate(this);
    }

    public void setTanent(User tanent) {
        this.tanent = tanent;
        tanent.setTanentEstate(this);
    }

    public void setBrokerConfirmY() {
        this.brokerConfirmYN = true;
    }
    public void setOwnerConfirmY() {
        this.ownerConfirmYN = true;
    }

    //아예 초기화한 후 대입
    public void addMedia(Media media) {
        if(this.estateMedia == null) {
            estateMedia = new ArrayList<>();
        }
        this.estateMedia.add(media);
    }

    public void addAsset(Asset asset) {
        if(this.assets == null) {
            this.assets = new ArrayList<>();
        }
        this.assets.add(asset);
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
    public void setId(Long id) { this.id = id; }
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
