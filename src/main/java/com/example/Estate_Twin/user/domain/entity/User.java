package com.example.Estate_Twin.user.domain.entity;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.estate.domain.entity.DipEstate;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.estate.domain.entity.EstateType;
import com.example.Estate_Twin.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @Column
    private LocalDate birthday;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String name;

    @Email
    @NotBlank(message = "아이디는 null일 수 없습니다!")
    private String email;

    @OneToOne(mappedBy = "user")
    private Address address;

    @Column
    private EstateType estateType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //세입중인 매물
    @OneToOne(mappedBy = "tanent")
    private Estate tanentEstate;

    //소유한 매물
    @OneToMany(
            mappedBy = "owner",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Estate> ownEstate = new ArrayList<>();

    //찜한 매물
    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private Set<DipEstate> dipEstates = new HashSet<>();

    @Builder
    public User(LocalDate birthday, String phone, String name, String email,
                Address address, EstateType estateType, Role role) {
        this.birthday = birthday;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.address = address;
        this.estateType = estateType;
        this.role = role;
    }
    public void setEstate(Estate estate) {
        this.tanentEstate = estate;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
