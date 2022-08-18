package com.example.Estate_Twin.user.domain.entity;

import com.example.Estate_Twin.util.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@MappedSuperclass
public abstract class BaseEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private Date birthday;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String name;

    @Email
    @NotNull(message = "아이디는 null일 수 없습니다!")
    private String email;

}
