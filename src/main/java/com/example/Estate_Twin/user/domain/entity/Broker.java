package com.example.Estate_Twin.user.domain.entity;

import com.example.Estate_Twin.address.data.entity.Address;
import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.media.domain.entity.Media;
import com.example.Estate_Twin.media.web.dto.MediaDto;
import lombok.*;
import org.springframework.data.repository.cdi.Eager;

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
    @Column(nullable = false)
    private String businessName;
    //대표명
    @Column(nullable = false)
    private String agentName;
    //중개등록번호
    @Column(nullable = false)
    private String brokerageRegistrationNumber;
    //사업자 등록 번호
    @Column(nullable = false)
    private String businessRegistrationNumber;
    //사업자 등록증(src)
    @Column(nullable = false)
    private String businessLicense;
    //중개등록증(src)
    @Column(nullable = false)
    private String brokerageRegistrationLicense;
    //거래 완료 건 수
    private Long countOfTransactionCompletion;
    private String brokerPhoto;
    //소개글
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    //주소
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    //단방향
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "broker", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Estate> tradeEstates;
    @Builder
    public Broker(String businessName, String agentName, String brokerageRegistrationNumber, String businessRegistrationNumber,
                  String businessLicense, String brokerageRegistrationLicense, Long countOfTransactionCompletion, String content,
                  Address address, String brokerPhoto) {
        this.businessName = businessName;
        this.agentName = agentName;
        this.brokerageRegistrationNumber = brokerageRegistrationNumber;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessLicense = businessLicense;
        this.brokerageRegistrationLicense = brokerageRegistrationLicense;
        this.countOfTransactionCompletion = countOfTransactionCompletion;
        this.content = content;
        this.address = address;
        this.brokerPhoto = brokerPhoto;
    }
    public void setUser(User user) {
        this.user = user;
        user.setIsBroker();
    }
    @PrePersist
    public void prePersist() {
        this.tradeEstates = new HashSet<>();
        this.countOfTransactionCompletion = 0L;
    }
}
