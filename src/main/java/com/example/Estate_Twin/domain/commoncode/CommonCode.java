package com.example.Estate_Twin.domain.commoncode;

import lombok.Builder;
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

    @Builder // 빌더 형태로 만들어줌
    public CommonCode(String codeType,String codeNo,String codeName,String parentNo
    ) {//생성자
        this.codeType = codeType;
        this.codeNo = codeNo;
        this.codeName = codeName;
        this.parentNo = parentNo;
    }
}
