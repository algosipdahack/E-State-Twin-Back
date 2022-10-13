package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.asset.data.entity.Asset;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.web.dto.*;
import com.example.Estate_Twin.exception.*;
import com.example.Estate_Twin.exception.Exception;
import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.house.domain.entity.House;
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
    @Column(columnDefinition = "TEXT")
    private String content;
    //s3에 올려진 model src
    private String model;
    //s3에 올려진 3D 모델의 썸네일 -> 자동으로 업로드 되게끔(lambda -> s3)
    private String thumbnail3D;
    //매물 영상 동영상
    private String arCam;
    private boolean isPosted;
    private boolean ownerConfirmYN;
    private boolean brokerConfirmYN;
    @Embedded
    private Address address;
    @ElementCollection
    private List<String> estateMedia;
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private State state;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @OneToOne
    @JoinColumn(name = "estatehit_id")
    private EstateHit estateHit;
    @OneToOne
    @JoinColumn(name = "house_id")
    private House house;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id")
    private Broker broker;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToOne(mappedBy = "tenentEstate")
    private User tenent;
    @OneToMany(mappedBy = "estate", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Asset> assets;
    //찜한 * 최근 * 문의한 매물
    @OneToMany(mappedBy = "estate", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<PreferEstate> preferEstates;

    @Builder // 빌더 형태로 만들어줌
    public Estate(Broker broker, User owner, Address address) {
        setOwner(owner);
        setBroker(broker);
        this.address = address;
    }

    public Estate detailUpdate(EstateSaveRequestDto dto) {
        this.content = dto.getContent();
        this.estateThumbNail = dto.getEstateThumbNail();
        this.transactionType = TransactionType.of(dto.getTransactionType());
        this.model = dto.getModel();
        this.arCam = dto.getArCam();
        this.estateMedia.clear();
        this.estateMedia.addAll(dto.getEstatePhotos());
        return this;
    }

    public Estate update(EstateUpdateRequestDto dto) {
        this.content = dto.getContent();
        this.model = dto.getModel();
        this.transactionType = TransactionType.of(dto.getTransactionType());
        this.estateThumbNail = dto.getEstateThumbNail();
        this.address = dto.getAddress();
        return this;
    }

    public void setHouse(House house) {
        this.house = house;
        house.setEstate(this);
    }

    public void setEstateHit(EstateHit estateHit) {
        this.estateHit = estateHit;
        estateHit.setEstate(this);
    }

    public Estate updateEstateHit() {
        this.estateHit.updateHit();
        return this;
    }
    //TODO Optional 적용
    public void setOwner(User owner) {
        if(this.owner != null ){
            this.owner.getOwnEstates().remove(this);
        }
        this.owner = owner;
        owner.getOwnEstates().add(this);
    }

    public void setBroker(Broker broker) {
        if(this.broker != null ){
            this.broker.getTradeEstates().remove(this);
        }
        this.broker = broker;
        broker.getTradeEstates().add(this);
    }

    public Estate setTenent(User tenent) {
        this.tenent = tenent;
        return this;
    }

    public void setGrade(Grade grade) {
        if(this.isPosted == false) {
            throw new BadRequestException("게시되지 않은 매물은 뱃지를 가질 수 없습니다!");
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
        if(!this.isBrokerConfirmYN() || !this.isOwnerConfirmYN()) {
            throw new Exception("브로커와 집주인 모두 confirm을 해야 합니다.");
        }
        this.isPosted = true;
        this.state = State.POST_DONE;
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    //insert 되기 전 실행된다
    @PrePersist
    public void prePersist() {
        this.estateMedia = new ArrayList<>();
        this.assets = new HashSet<>();
        this.preferEstates = new HashSet<>();
        this.state = this.state == null ? State.BROKER_BEFORE : this.state;
        this.isPosted = false;
        this.ownerConfirmYN = false;
        this.brokerConfirmYN = false;
    }

}
