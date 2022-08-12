package com.example.Estate_Twin.estate.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "estatehit")
public class EstateHit {
    @Id
    @Column(name = "estatehit_id")
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @Column
    private Long totalHit;

    @Column
    private Long weeklyHit;

    @Builder // 빌더 형태로 만들어줌
    public EstateHit(LocalDateTime date, Estate estate, Long totalHit, Long weeklyHit
    ) {//생성자
        this.date = date;
        this.estate = estate;
        this.totalHit = totalHit;
        this.weeklyHit = weeklyHit;
    }

    public void update_hit() {
        this.weeklyHit++;
        this.totalHit++;
    }
}
