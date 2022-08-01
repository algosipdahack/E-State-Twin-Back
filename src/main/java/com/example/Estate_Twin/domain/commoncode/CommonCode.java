package com.example.Estate_Twin.domain.commoncode;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "commoncode")
public class CommonCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commoncode_id")
    private long id;

    @Column
    private String codeType;

    @Column
    private String codeNo;

    @Column
    private String codeName;

    @Column
    private String parentNo;
}
