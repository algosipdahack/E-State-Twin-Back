package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.util.BaseTimeEntity;
import com.example.Estate_Twin.user.domain.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "preferEstate")
public class PreferEstate extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name="prefer_estate_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="estate_id")
    private Estate estate;
    @Enumerated(value = EnumType.STRING)
    private Preference preference;

    @Builder
    public PreferEstate(User user, Estate estate, Preference preference) {
        this.user = user;
        this.estate = estate;
        this.preference = preference;
    }

}
