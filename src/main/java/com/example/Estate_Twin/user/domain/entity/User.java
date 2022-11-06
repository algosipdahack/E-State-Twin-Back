package com.example.Estate_Twin.user.domain.entity;

import com.example.Estate_Twin.estate.domain.entity.*;
import com.example.Estate_Twin.user.web.dto.UserSignUpDto;
import com.example.Estate_Twin.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
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
    @Column(length = 11)
    private String phone;
    //OAuth
    private String name;
    @Email
    private String email;
    private String refreshToken;
    private boolean isBroker;
    private boolean userDel;
    //선호 지역
    private String borough;
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private EstateType estateType;
    @Enumerated(EnumType.STRING)
    private Role role;
    //세입중인 매물
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private Estate tenantEstate;
    //소유한 매물
    @OneToMany(mappedBy = "owner", orphanRemoval = true,
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Estate> ownEstates;
    //찜한 매물
    @OneToMany(mappedBy = "user", orphanRemoval = true,
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PreferEstate> preferEstates;

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

    public User signup(UserSignUpDto dto) {
        this.birthday = dto.getBirthday();
        this.phone = dto.getPhone();
        this.transactionType = TransactionType.of(dto.getTransactionType());
        this.estateType = EstateType.of(dto.getEstateType());
        this.borough = dto.getBorough();
        return this;
    }

    public User setTenantEstate(Estate estate) {
        this.tenantEstate = estate;
        estate.setTenant(this);
        return this;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setIsBroker() {
        this.isBroker = true;
    }

    public User setBorough(String borough) {
        this.borough = borough;
        return this;
    }

    public User delUser() {
        this.userDel = true;
        return this;
    }

    public void setUserDelFalse() {
        this.userDel = false;
    }

    @PrePersist
    public void prePersist() {
        this.isBroker = false;
        this.userDel = false;
        this.ownEstates = new HashSet<>();
        this.preferEstates = new HashSet<>();
    }
}
