package com.example.Estate_Twin.domain.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class Range {
    @Column
    private Long max;
    private Long min;
}
