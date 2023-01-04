package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.exception.CheckHouseException;
import com.example.Estate_Twin.exception.ErrorCode;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.house.domain.entity.House;
import com.example.Estate_Twin.user.domain.entity.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estate")
public class Estate extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estate_id")
    private Long id;
    //리스트에서 보여줄 썸네일
    private String estateThumbNail;
    //2D 도면
    private String floorplan;
    //s3에 올려진 model src
    private String model;
    //s3에 올려진 3D 모델의 썸네일 -> 자동으로 업로드 되게끔(lambda -> s3)
    private String thumbnail3D;
    //매물 영상 동영상
    private String arCam;
    private boolean isPosted = false;
    private boolean ownerConfirmYN = false;
    private boolean brokerConfirmYN = false;
    @Embedded
    private Address address;
    @ElementCollection
    private List<String> estatePhoto = new ArrayList<>();
    @ElementCollection
    private List<String> estateVideo = new ArrayList<>();
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private State state = State.BROKER_BEFORE;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estatehit_id")
    private EstateHit estateHit;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id")
    private Broker broker;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToOne(mappedBy = "tenantEstate", fetch = FetchType.LAZY)
    private User tenant;
    @OneToMany(mappedBy = "estate", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Asset> assets = new ArrayList<>();
    //찜한 * 최근 * 문의한 매물
    @OneToMany(mappedBy = "estate", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PreferEstate> preferEstates = new ArrayList<>();

    @Builder // 빌더 형태로 만들어줌
    public Estate(Broker broker, User owner, Address address) {
        setOwner(owner);
        setBroker(broker);
        this.address = address;
    }

    public Estate detailUpdate(EstateSaveRequestDto dto) {
        this.estateThumbNail = dto.getEstatePhotos().get(0);
        this.transactionType = TransactionType.of(dto.getTransactionType());
        this.floorplan = dto.getFloorplan();
        this.arCam = dto.getArCam();
        this.estatePhoto.clear();
        this.estatePhoto.addAll(dto.getEstatePhotos());
        this.estateVideo.clear();
        this.estateVideo.addAll(dto.getEstateVideos());
        return this;
    }

    public void update(EstateUpdateRequestDto dto) {
        this.model = dto.getModel();
        this.transactionType = TransactionType.of(dto.getTransactionType());
        this.estateThumbNail = dto.getEstateThumbNail();
        this.address = dto.getAddress();
    }

    public void setHouse(House house) {
        this.house = house;
        house.setEstate(this);
    }

    public void setEstateHit(EstateHit estateHit) {
        this.estateHit = estateHit;
        estateHit.setEstate(this);
    }

    public void setOwner(User owner) {
        Optional<User> user = Optional.of(this.owner);
        user.ifPresent(it -> it.getOwnEstates().remove(this));
        this.owner = owner;
        owner.getOwnEstates().add(this);
    }

    public void setBroker(Broker broker) {
        Optional<Broker> user = Optional.of(this.broker);
        user.ifPresent(it -> it.getTradeEstates().remove(this));
        this.broker = broker;
        broker.getTradeEstates().add(this);
    }

    public Estate setTenant(User tenant) {
        this.tenant = tenant;
        return this;
    }

    public void setGrade(Grade grade) {
        if(!this.isPosted) {
            throw new CheckHouseException(ErrorCode.GRADE_NOT_ALLOW_FOR_NOT_POSTED);
        }
        this.grade = grade;
    }

    public Estate setBrokerConfirmY() {
        this.brokerConfirmYN = true;
        return this;
    }

    public Estate setOwnerConfirmY() {
        this.ownerConfirmYN = true;
        return this;
    }
    public Estate setIsPosted() {
        if(!(this.isBrokerConfirmYN() && this.isOwnerConfirmYN())) {
            throw new CheckHouseException(ErrorCode.BROKER_OR_OWNER_YET_CONFIRMED);
        }
        this.isPosted = true;
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

}
