package com.example.Estate_Twin.estate.domain.entity;

import com.example.Estate_Twin.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "estatehit")
public class EstateHit extends BaseTimeEntity {
    @Id
    @Column(name = "estatehit_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @Column
    private Long totalHit;

    @Column
    private Long weeklyHit;

    @Builder // 빌더 형태로 만들어줌
    public EstateHit(Long totalHit, Long weeklyHit
    ) {//생성자
        this.totalHit = totalHit;
        this.weeklyHit = weeklyHit;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public void updateHit() {
        this.weeklyHit += 1;
        this.totalHit += 1;
    }

    @PrePersist
    public void prePersist() {
        this.weeklyHit = this.weeklyHit == null ? 0 : this.weeklyHit;
        this.totalHit = this.totalHit == null ? 0 : this.totalHit;
    }
}