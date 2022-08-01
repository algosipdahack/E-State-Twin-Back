package com.example.Estate_Twin.domain.estate;

import com.example.Estate_Twin.domain.constractstate.ConstractState;
import com.example.Estate_Twin.domain.house.House;
import com.example.Estate_Twin.domain.media.Media;
import com.example.Estate_Twin.domain.user.Broker;
import com.example.Estate_Twin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "estatehit")
public class EstateHit {
    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "estatehit_id")
    private Date date;

    @OneToOne(mappedBy = "estateHit")
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
