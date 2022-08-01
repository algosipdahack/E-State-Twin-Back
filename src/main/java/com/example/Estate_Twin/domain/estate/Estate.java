package com.example.Estate_Twin.domain.estate;

import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.domain.constractstate.ConstractState;
import com.example.Estate_Twin.domain.house.House;
import com.example.Estate_Twin.domain.media.Media;
import com.example.Estate_Twin.domain.user.Broker;
import com.example.Estate_Twin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "estate")
public class Estate extends BaseTimeEntity {
    @EmbeddedId
    @Column(name = "estate_id")
    private EstateId estateId;

    @MapsId("houseId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    @MapsId("brokerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id")
    private Broker broker;

    @MapsId("ownerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Embedded private EstateNo estateNo;

    @OneToMany(
            mappedBy = "estate",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Media> estateMedia = new ArrayList<>();


    @OneToOne
    @JoinColumn(name="constractstate_id")
    private ConstractState constractState;

    @OneToOne
    @JoinColumn(name="estatehit_id")
    private EstateHit estateHit;

    @Column
    private String TransactionType;

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
    private String distinct;

    @Column
    private String address;


    @Builder // 빌더 형태로 만들어줌
    public Estate(List<Media> estateMedia, String content, Rank rank, String model, String arCam
    ) {//생성자
        //his.estateMedia = estateMedia;
        this.content = content;
        this.rank = rank;
        this.model = model;
        this.arCam = arCam;
    }
}
