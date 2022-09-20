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
    private String name;
    private String email;
    private String refreshToken;
    private boolean isBroker;

    //TODO 선호지역 받기
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private EstateType estateType;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    //TODO 선호 지역때문에 넣은 듯. 빼기
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
    //세입중인 매물
    @OneToOne(mappedBy = "tanent")
    private Estate tanentEstate;
    //소유한 매물
    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    private List<Estate> ownEstates = new ArrayList<>();
    //찜한 매물
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<DipEstate> dipEstates = new HashSet<>();

    @Builder
    public User(LocalDate birthday, String phone, String name, String email,
                Address address, EstateType estateType, TransactionType transactionType, Role role,
                AuthProvider authProvider, String refreshToken) {
        this.birthday = birthday;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.address = address;
        this.estateType = estateType;
        this.role = role;
        this.transactionType = transactionType;
        this.authProvider = authProvider;
        this.refreshToken = refreshToken;
    }
    public void setTanentEstate(Estate estate) {
        this.tanentEstate = estate;
    }
    public void setAddress(Address address) {
        this.address = address;
        this.address.setUser(this);
    }
    public void setIsBroker() {
        this.isBroker = true;
    }
    @PrePersist
    public void prePersist() {
        this.isBroker = false;
        //TODO 디폴트로 박기
        String string = "2019-01-10";
        this.birthday = LocalDate.parse(string, DateTimeFormatter.ISO_DATE);

        this.estateType = EstateType.APARTMENT;
        this.transactionType = TransactionType.LEASE;
        this.phone = "010-0000-0000";
        this.refreshToken = "000-000-0000";
    }
}
