package com.example.Estate_Twin.user.domain.entity;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.estate.domain.entity.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
@AttributeOverride(name = "id", column = @Column(name = "USER_ID"))
public class User extends BaseEntity {

    @Column
    private String estateType;

    @OneToOne(mappedBy = "estate")
    private Address broker_address;

    @OneToMany(
            mappedBy = "owner",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true // DB에서 함께 삭제됨
    )
    private List<Estate> estates = new ArrayList<>();

    @OneToMany(
            mappedBy = "tanent",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true // DB에서 함께 삭제됨
    )
    private List<DipRecentEstate> dipRecentEstates = new ArrayList<>();

}
