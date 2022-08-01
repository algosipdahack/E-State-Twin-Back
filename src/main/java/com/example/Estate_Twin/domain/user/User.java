package com.example.Estate_Twin.domain.user;

import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.domain.estate.DipRecentEstate;
import com.example.Estate_Twin.domain.estate.Estate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true // DB에서 함께 삭제됨
    )
    private List<Estate> estates = new ArrayList<>();

    @OneToMany(
            mappedBy = "tanent",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true // DB에서 함께 삭제됨
    )
    private List<DipRecentEstate> dipRecentEstates = new ArrayList<>();

}
