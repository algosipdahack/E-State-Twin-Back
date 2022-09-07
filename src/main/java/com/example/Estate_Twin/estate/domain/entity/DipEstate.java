package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "dipEstate")
public class DipEstate extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name="dip_estate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="estate_id")
    private Estate estate;

}
