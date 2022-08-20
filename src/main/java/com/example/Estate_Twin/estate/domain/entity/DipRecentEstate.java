package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.user.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "diprecentestate")
public class DipRecentEstate extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diprecentestate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User tanent;

    private boolean flag;
}
