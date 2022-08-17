package com.example.Estate_Twin.user.domain;

import com.example.Estate_Twin.estate.domain.DipRecentEstate;
import com.example.Estate_Twin.estate.domain.Estate;
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

    @Column
    private String address;

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
