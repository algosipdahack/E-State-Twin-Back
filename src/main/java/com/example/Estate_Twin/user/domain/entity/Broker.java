package com.example.Estate_Twin.user.domain.entity;

import com.example.Estate_Twin.contractstate.domain.entity.State;
import com.example.Estate_Twin.estate.domain.entity.Estate;
import com.example.Estate_Twin.media.domain.entity.Media;
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
    @Column(nullable = false)
    private String businessName;
    @Column(nullable = false)
    private String agentName;
    @Column(nullable = false)
    private String brokerageRegistrationNumber;
    @Column(nullable = false)
    private String businessRegistrationNumber;
    @Column(nullable = false)
    private String businessLicense;
    @Column(nullable = false)
    private String brokerageRegistrationLicense;
    //단방향
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "broker", orphanRemoval = true)
    private Set<Estate> tradeEstates;
    @OneToMany(mappedBy = "broker", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Media> brokerPhoto;
    @Builder
    public Broker(String businessName, String agentName, String brokerageRegistrationNumber, String businessRegistrationNumber,
                  String businessLicense, String brokerageRegistrationLicense) {
        this.businessName = businessName;
        this.agentName = agentName;
        this.brokerageRegistrationNumber = brokerageRegistrationNumber;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessLicense = businessLicense;
        this.brokerageRegistrationLicense = brokerageRegistrationLicense;
    }
    public void setUser(User user) {
        this.user = user;
        user.setIsBroker();
    }
    @PrePersist
    public void prePersist() {
        this.tradeEstates = new HashSet<>();
        this.brokerPhoto = new HashSet<>();
    }
}
