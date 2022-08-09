package com.example.Estate_Twin.user.domain;

import com.example.Estate_Twin.util.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@MappedSuperclass
public abstract class BaseEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date birthday;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

}
