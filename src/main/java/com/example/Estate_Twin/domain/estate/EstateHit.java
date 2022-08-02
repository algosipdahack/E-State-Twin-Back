package com.example.Estate_Twin.domain.estate;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "estatehit")
public class EstateHit {
    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "estatehit_id")
    private Date date;

    @OneToOne
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @Column
    private long totalHit;

    @Column
    private long weeklyHit;

    @Builder // 빌더 형태로 만들어줌
    public EstateHit( Date date, Estate estate, long totalHit, long weeklyHit
    ) {//생성자
        this.date = date;
        this.estate = estate;
        this.totalHit = totalHit;
        this.weeklyHit = weeklyHit;
    }
}
