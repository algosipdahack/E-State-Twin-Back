package com.example.Estate_Twin.domain.estate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.util.Date;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstateNo {
    private Date date;
    private int cnt;
}
