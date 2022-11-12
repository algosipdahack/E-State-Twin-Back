package com.example.Estate_Twin.user.domain.entity;

import com.example.Estate_Twin.address.Address;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "broker")
public class Broker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broker_id")
    private Long id;
    // broker column
    //상호명
    private String businessName;
    //대표명
    private String agentName;
    //중개등록번호
    private String brokerageRegistrationNumber;
    //사업자 등록 번호
    private String businessRegistrationNumber;
    //사업자 등록증(src)
    private String businessLicense;
    //중개등록증(src)
    private String brokerageRegistrationLicense;
    //거래 완료 건 수
    private Long countOfTransactionCompletion = 0L;
    private String brokerPhoto;
    //소개글
    @Column(columnDefinition = "TEXT")
    private String content;
    //주소
    @Embedded
    private Address address;
    //단방향
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "broker", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Estate> tradeEstates = new ArrayList<>();

    @Builder
    public Broker(String businessName, String agentName, String brokerageRegistrationNumber, String businessRegistrationNumber,
                  String businessLicense, String brokerageRegistrationLicense, String content,
                  Address address, String brokerPhoto) {
        this.businessName = businessName;
        this.agentName = agentName;
        this.brokerageRegistrationNumber = brokerageRegistrationNumber;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessLicense = businessLicense;
        this.brokerageRegistrationLicense = brokerageRegistrationLicense;
        this.content = content;
        this.address = address;
        this.brokerPhoto = brokerPhoto;
    }

    public void setUser(User user) {
        this.user = user;
        user.setIsBroker();
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
