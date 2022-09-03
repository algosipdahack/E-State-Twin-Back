package com.example.Estate_Twin.estate.web.dto;

import com.example.Estate_Twin.estate.domain.entity.EstateHit;
import lombok.Getter;

@Getter
public class EstateHitDto {
    private final Long totalHit;
    private final Long weeklyHit;
    public EstateHitDto(EstateHit estateHit) {
        this.totalHit = estateHit.getTotalHit();
        this.weeklyHit = estateHit.getWeeklyHit();
    }
}
