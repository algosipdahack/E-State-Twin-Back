package com.example.Estate_Twin.user.domain.entity;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    //LocalDate => 날짜 정보만 출력. ex) 2019-11-13
    private LocalDate birthday;
    @Column(unique = true)
    private String phone;
    //OAuth
    private String name;
    private String email;
    private String refreshToken;
    private boolean isBroker;
    //선호 지역
    private String borough;
    //매물 정보 중 arCam 비활성화
    private boolean isArCam;
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private EstateType estateType;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    //세입중인 매물
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private Estate tanentEstate;
    //소유한 매물
    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    private Set<Estate> ownEstates = new HashSet<>();
    //찜한 매물
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<DipEstate> dipEstates = new HashSet<>();

    @Builder
    public User(LocalDate birthday, String phone, String name, String email,
                String borough, EstateType estateType, TransactionType transactionType, Role role,
                AuthProvider authProvider, String refreshToken) {
        this.birthday = birthday;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.estateType = estateType;
        this.role = role;
        this.borough = borough;
        this.transactionType = transactionType;
        this.authProvider = authProvider;
        this.refreshToken = refreshToken;
    }
    public void setTanentEstate(Estate estate) {
        this.tanentEstate = estate;
        estate.setTanent(this);
    }
    public void setIsBroker() {
        this.isBroker = true;
    }
    public void setIsArCam() { this.isArCam = true; }
    public User update(LocalDate birthday, String phone, TransactionType transactionType, EstateType estateType, String borough) {
        this.birthday = birthday;
        this.phone = phone;
        this.transactionType = transactionType;
        this.estateType = estateType;
        this.borough = borough;
        return this;
    }
    @PrePersist
    public void prePersist() {
        this.isBroker = false;
        this.isArCam = false;
    }
}
