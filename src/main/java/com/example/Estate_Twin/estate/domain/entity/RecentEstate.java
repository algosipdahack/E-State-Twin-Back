package com.example.Estate_Twin.estate.domain.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@RedisHash(value = "RecentEstate")
public class RecentEstate {
    @Id private final Long userId;
    private final Long estateId;
    private final LocalDateTime createdDate;

    @Builder
    public RecentEstate(Long userId, Long estateId, LocalDateTime createdDate) {
        this.userId = userId;
        this.estateId = estateId;
        this.createdDate = createdDate;
    }
}
