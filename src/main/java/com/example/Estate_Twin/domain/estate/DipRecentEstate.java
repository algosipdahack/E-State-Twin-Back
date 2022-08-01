package com.example.Estate_Twin.domain.estate;

import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "diprecentestate")
public class DipRecentEstate extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diprecentestate_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User tanent;

    private boolean flag;
}
